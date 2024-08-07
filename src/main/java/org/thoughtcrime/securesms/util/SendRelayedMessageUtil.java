package org.thoughtcrime.securesms.util;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.b44t.messenger.DcContext;
import com.b44t.messenger.DcMsg;

import org.thoughtcrime.securesms.ConversationListRelayingActivity;
import org.thoughtcrime.securesms.connect.DcHelper;
import org.thoughtcrime.securesms.mms.PartAuthority;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

import static org.thoughtcrime.securesms.util.RelayUtil.getForwardedMessageIDs;
import static org.thoughtcrime.securesms.util.RelayUtil.getSharedText;
import static org.thoughtcrime.securesms.util.RelayUtil.getSharedSubject;
import static org.thoughtcrime.securesms.util.RelayUtil.getSharedHtml;
import static org.thoughtcrime.securesms.util.RelayUtil.getSharedType;
import static org.thoughtcrime.securesms.util.RelayUtil.getSharedUris;
import static org.thoughtcrime.securesms.util.RelayUtil.isForwarding;
import static org.thoughtcrime.securesms.util.RelayUtil.isSharing;
import static org.thoughtcrime.securesms.util.RelayUtil.resetRelayingMessageContent;

public class SendRelayedMessageUtil {
  private static final String TAG = SendRelayedMessageUtil.class.getSimpleName();

  public static void immediatelyRelay(Activity activity, int chatId) {
    immediatelyRelay(activity, new Long[]{(long) chatId});
  }

  public static void immediatelyRelay(Activity activity, final Long[] chatIds) {
    ConversationListRelayingActivity.finishActivity();
    if (isForwarding(activity)) {
      int[] forwardedMessageIDs = getForwardedMessageIDs(activity);
      resetRelayingMessageContent(activity);
      Util.runOnAnyBackgroundThread(() -> {
        for (long chatId : chatIds) {
          handleForwarding(activity, (int) chatId, forwardedMessageIDs);
        }

      });
    } else if (isSharing(activity)) {
      ArrayList<Uri> sharedUris = getSharedUris(activity);
      String sharedText = getSharedText(activity);
      String subject = getSharedSubject(activity);
      String sharedHtml = getHtml(activity, getSharedHtml(activity));
      String msgType = getSharedType(activity);
      resetRelayingMessageContent(activity);
      Util.runOnAnyBackgroundThread(() -> {
        for (long chatId : chatIds) {
          handleSharing(activity, (int) chatId, sharedUris, msgType, sharedHtml, subject, sharedText);
        }
      });
    }
  }

  private static void handleForwarding(Context context, int chatId, int[] forwardedMessageIDs) {
    DcContext dcContext = DcHelper.getContext(context);
    dcContext.forwardMsgs(forwardedMessageIDs, chatId);
  }

  private static void handleSharing(Context context, int chatId, ArrayList<Uri> sharedUris, String msgType, String sharedHtml, String subject, String sharedText) {
    DcContext dcContext = DcHelper.getContext(context);
    ArrayList<Uri> uris = sharedUris;
    String text = sharedText;

    if (uris.size() == 1) {
      dcContext.sendMsg(chatId, createMessage(context, uris.get(0), msgType, sharedHtml, subject, text));
    } else {
      if (text != null || sharedHtml != null) {
        dcContext.sendMsg(chatId, createMessage(context, null, null, sharedHtml, subject, text));
      }
      for (Uri uri : uris) {
        dcContext.sendMsg(chatId, createMessage(context, uri, null, null, subject, null));
      }
    }
  }

  public static DcMsg createMessage(Context context, Uri uri, String type, String html, String subject, String text) throws NullPointerException {
    DcContext dcContext = DcHelper.getContext(context);
    DcMsg message;
    String mimeType = MediaUtil.getMimeType(context, uri);
    if (uri == null) {
      message = new DcMsg(dcContext, DcMsg.DC_MSG_TEXT);
    } else if ("sticker".equals(type)) {
      message = new DcMsg(dcContext, DcMsg.DC_MSG_STICKER);
    } else if ("image".equals(type) || MediaUtil.isImageType(mimeType)) {
      message = new DcMsg(dcContext, DcMsg.DC_MSG_IMAGE);
    } else if ("audio".equals(type) || MediaUtil.isAudioType(mimeType)) {
      message = new DcMsg(dcContext, DcMsg.DC_MSG_AUDIO);
    } else if ("video".equals(type) || MediaUtil.isVideoType(mimeType)) {
      message = new DcMsg(dcContext, DcMsg.DC_MSG_VIDEO);
    } else {
      message = new DcMsg(dcContext, DcMsg.DC_MSG_FILE);
    }

    if (uri != null) {
      message.setFile(getRealPathFromUri(context, uri), mimeType);
    }
    if (html != null) {
      message.setHtml(html);
    }
    if (subject != null) {
      message.setSubject(subject);
    }
    if (text != null) {
      message.setText(text);
    }
    return message;
  }

  private static String getRealPathFromUri(Context context, Uri uri) throws NullPointerException {
    DcContext dcContext = DcHelper.getContext(context);
    try {
      String filename = uri.getPathSegments().get(2); // Get real file name from Uri
      String ext = "";
      int i = filename.lastIndexOf(".");
      if (i >= 0) {
        ext = filename.substring(i);
        filename = filename.substring(0, i);
      }
      String path = DcHelper.getBlobdirFile(dcContext, filename, ext);

      // copy content to this file
      if (path != null) {
        InputStream inputStream = PartAuthority.getAttachmentStream(context, uri);
        OutputStream outputStream = new FileOutputStream(path);
        Util.copy(inputStream, outputStream);
      }

      return path;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private static String getHtml(Context context, Uri uri) {
    try {
      InputStream in = PartAuthority.getAttachmentStream(context, uri);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      StringBuilder html = new StringBuilder();
      for (String line; (line = br.readLine()) != null; ) {
        html.append(line).append('\n');
      }
      if (in != null) in.close();
      br.close();
      return html.toString();
    } catch (Exception ex) {
      Log.e(TAG, "failed to get HTML", ex);
      return null;
    }
  }
}
