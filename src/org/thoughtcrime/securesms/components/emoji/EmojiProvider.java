package org.thoughtcrime.securesms.components.emoji;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.widget.TextView;

import org.thoughtcrime.securesms.R;
import org.thoughtcrime.securesms.components.emoji.parsing.EmojiDrawInfo;
import org.thoughtcrime.securesms.components.emoji.parsing.EmojiPageBitmap;
import org.thoughtcrime.securesms.components.emoji.parsing.EmojiParser;
import org.thoughtcrime.securesms.components.emoji.parsing.EmojiTree;
import org.thoughtcrime.securesms.util.FutureTaskListener;
import org.thoughtcrime.securesms.util.Pair;
import org.thoughtcrime.securesms.util.StringUtil;
import org.thoughtcrime.securesms.util.Util;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

public class EmojiProvider {

  private static final    String        TAG      = EmojiProvider.class.getSimpleName();
  private static volatile EmojiProvider instance = null;
  private static final    Paint         paint    = new Paint(Paint.FILTER_BITMAP_FLAG | Paint.ANTI_ALIAS_FLAG);
  private static final Pattern EMOJI_PATTERN = Pattern.compile("^(?:(?:[\u00a9\u00ae\u203c\u2049\u2122\u2139\u2194-\u2199\u21a9-\u21aa\u231a-\u231b\u2328\u23cf\u23e9-\u23f3\u23f8-\u23fa\u24c2\u25aa-\u25ab\u25b6\u25c0\u25fb-\u25fe\u2600-\u2604\u260e\u2611\u2614-\u2615\u2618\u261d\u2620\u2622-\u2623\u2626\u262a\u262e-\u262f\u2638-\u263a\u2648-\u2653\u2660\u2663\u2665-\u2666\u2668\u267b\u267f\u2692-\u2694\u2696-\u2697\u2699\u269b-\u269c\u26a0-\u26a1\u26aa-\u26ab\u26b0-\u26b1\u26bd-\u26be\u26c4-\u26c5\u26c8\u26ce-\u26cf\u26d1\u26d3-\u26d4\u26e9-\u26ea\u26f0-\u26f5\u26f7-\u26fa\u26fd\u2702\u2705\u2708-\u270d\u270f\u2712\u2714\u2716\u271d\u2721\u2728\u2733-\u2734\u2744\u2747\u274c\u274e\u2753-\u2755\u2757\u2763-\u2764\u2795-\u2797\u27a1\u27b0\u27bf\u2934-\u2935\u2b05-\u2b07\u2b1b-\u2b1c\u2b50\u2b55\u3030\u303d\u3297\u3299\ud83c\udc04\ud83c\udccf\ud83c\udd70-\ud83c\udd71\ud83c\udd7e-\ud83c\udd7f\ud83c\udd8e\ud83c\udd91-\ud83c\udd9a\ud83c\ude01-\ud83c\ude02\ud83c\ude1a\ud83c\ude2f\ud83c\ude32-\ud83c\ude3a\ud83c\ude50-\ud83c\ude51\u200d\ud83c\udf00-\ud83d\uddff\ud83d\ude00-\ud83d\ude4f\ud83d\ude80-\ud83d\udeff\ud83e\udd00-\ud83e\uddff\udb40\udc20-\udb40\udc7f]|\u200d[\u2640\u2642]|[\ud83c\udde6-\ud83c\uddff]{2}|.[\u20e0\u20e3\ufe0f]+)+)+$");

  private final EmojiTree emojiTree = new EmojiTree();

  private static final int EMOJI_RAW_HEIGHT = 64;
  private static final int EMOJI_RAW_WIDTH  = 64;
  private static final int EMOJI_VERT_PAD   = 0;
  private static final int EMOJI_PER_ROW    = 16;

  private final float decodeScale;
  private final float verticalPad;

  public static EmojiProvider getInstance(Context context) {
    if (instance == null) {
      synchronized (EmojiProvider.class) {
        if (instance == null) {
          instance = new EmojiProvider(context);
        }
      }
    }
    return instance;
  }

  private EmojiProvider(Context context) {
    this.decodeScale = Math.min(1f, context.getResources().getDimension(R.dimen.emoji_drawer_size) / EMOJI_RAW_HEIGHT);
    this.verticalPad = EMOJI_VERT_PAD * this.decodeScale;

    for (EmojiPageModel page : EmojiPages.DATA_PAGES) {
      if (page.hasSpriteMap()) {
        EmojiPageBitmap pageBitmap = new EmojiPageBitmap(context, page, decodeScale);

        List<String> emojis = page.getEmoji();
        for (int i = 0; i < emojis.size(); i++) {
          emojiTree.add(emojis.get(i), new EmojiDrawInfo(pageBitmap, i));
        }
      }
    }

    for (Pair<String,String> obsolete : EmojiPages.OBSOLETE) {
      emojiTree.add(obsolete.first(), emojiTree.getEmoji(obsolete.second(), 0, obsolete.second().length()));
    }
  }

  @Nullable EmojiParser.CandidateList getCandidates(@Nullable CharSequence text) {
    if (text == null) return null;
    return new EmojiParser(emojiTree).findCandidates(text);
  }

  @Nullable Spannable emojify(@Nullable CharSequence text, @NonNull TextView tv) {
    return emojify(getCandidates(text), text, tv, false);
  }

  @Nullable Spannable emojify(@Nullable EmojiParser.CandidateList matches,
                              @Nullable CharSequence text,
                              @NonNull TextView tv,
                              boolean background) {
    if (matches == null || text == null) return null;
    SpannableStringBuilder      builder = new SpannableStringBuilder(text);

    for (EmojiParser.Candidate candidate : matches) {
      Drawable drawable = getEmojiDrawable(candidate.getDrawInfo(), background);

      if (drawable != null) {
        builder.setSpan(new EmojiSpan(drawable, tv), candidate.getStartIndex(), candidate.getEndIndex(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
    }

    return builder;
  }

  @Nullable Drawable getEmojiDrawable(CharSequence emoji) {
    EmojiDrawInfo drawInfo = emojiTree.getEmoji(emoji, 0, emoji.length());
    return getEmojiDrawable(drawInfo, false);
  }

  public boolean isEmoji(CharSequence emoji) {
     return emojiTree.getEmoji(emoji, 0, emoji.length()) != null;
  }

  /**
   * True if the text is likely a single, valid emoji. Otherwise false.
   *
   * We do a two-tier check: first using our own knowledge of emojis (which could be incomplete),
   * followed by a more wide check for all of the valid emoji unicode ranges (which could lead to
   * some false positives). YMMV.
   */
  public boolean maybeEmoji(CharSequence emoji) {
    if (Util.isEmpty(emoji)) {
      return false;
    }

    if (StringUtil.getGraphemeCount(emoji) != 1) {
      return false;
    }

    return isEmoji(emoji) || EMOJI_PATTERN.matcher(emoji).matches();
  }

  public @Nullable Bitmap getEmojiBitmap(CharSequence emoji, float scale, boolean background) {
    EmojiDrawInfo drawInfo = emojiTree.getEmoji(emoji, 0, emoji.length());
    EmojiDrawable drawable = ((EmojiDrawable) getEmojiDrawable(drawInfo, background));
    if (drawable != null) {
      return drawable.getEmojiBitmap(scale);
    }
    return null;
  }

  protected  @Nullable Drawable getEmojiDrawable(@Nullable EmojiDrawInfo drawInfo, boolean background) {
    if (drawInfo == null)  {
      return null;
    }
    final EmojiDrawable drawable = new EmojiDrawable(drawInfo, decodeScale);
    if (background) {
      try {
        drawable.setBitmap(drawInfo.getPage().loadPage(), background);
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      drawInfo.getPage().get().addListener(new FutureTaskListener<Bitmap>() {
        @Override public void onSuccess(final Bitmap result) {
          Util.runOnMain(() -> drawable.setBitmap(result));
        }

        @Override public void onFailure(ExecutionException error) {
          Log.w(TAG, error);
        }
      });
    }
    return drawable;
  }

  class EmojiDrawable extends Drawable {
    private final EmojiDrawInfo info;
    private       Bitmap        bmp;
    private final float         intrinsicWidth;
    private final float         intrinsicHeight;

    @Override
    public int getIntrinsicWidth() {
      return (int)intrinsicWidth;
    }

    @Override
    public int getIntrinsicHeight() {
      return (int)intrinsicHeight;
    }

    EmojiDrawable(EmojiDrawInfo info, float decodeScale) {
      this.info            = info;
      this.intrinsicWidth  = EMOJI_RAW_WIDTH  * decodeScale;
      this.intrinsicHeight = EMOJI_RAW_HEIGHT * decodeScale;
    }

    private Bitmap getEmojiBitmap(float scale) {
      Bitmap singleEmoji = Bitmap.createBitmap((int) (intrinsicWidth * scale), (int) (intrinsicHeight*scale), Bitmap.Config.ARGB_8888);

      final int row = info.getIndex() / EMOJI_PER_ROW;
      final int rowIndex = info.getIndex() % EMOJI_PER_ROW;

      Rect desRect = new Rect(0, 0, (int) intrinsicWidth, (int) intrinsicWidth);
      Rect srcRect = new Rect((int)(rowIndex * intrinsicWidth),
        (int)(row * intrinsicHeight + row * verticalPad)+1,
        (int)(((rowIndex + 1) * intrinsicWidth)-1),
        (int)((row + 1) * intrinsicHeight + row * verticalPad)-1);

      Canvas canvas = new Canvas(singleEmoji);
      canvas.scale(scale, scale);
      canvas.drawBitmap(bmp, srcRect, desRect, paint);

      return singleEmoji;
    }


    @Override
    public void draw(@NonNull Canvas canvas) {
      if (bmp == null) {
        return;
      }

      final int row = info.getIndex() / EMOJI_PER_ROW;
      final int row_index = info.getIndex() % EMOJI_PER_ROW;

      canvas.drawBitmap(bmp,
                        new Rect((int)(row_index * intrinsicWidth),
                                 (int)(row * intrinsicHeight + row * verticalPad)+1,
                                 (int)(((row_index + 1) * intrinsicWidth)-1),
                                 (int)((row + 1) * intrinsicHeight + row * verticalPad)-1),
                        getBounds(),
                        paint);
    }

    public void setBitmap(Bitmap bitmap) {
      setBitmap(bitmap, false);
    }

    public void setBitmap(Bitmap bitmap, boolean background) {
      if (!background) {
        Util.assertMainThread();
      }
      if (bmp == null || !bmp.sameAs(bitmap)) {
        bmp = bitmap;
        invalidateSelf();
      }
    }

    @Override
    public int getOpacity() {
      return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void setAlpha(int alpha) { }

    @Override
    public void setColorFilter(ColorFilter cf) { }
  }

}
