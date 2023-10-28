package org.thoughtcrime.securesms.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.b44t.messenger.DcContext;

import org.thoughtcrime.securesms.R;
import org.thoughtcrime.securesms.mms.GlideRequests;
import org.thoughtcrime.securesms.recipients.Recipient;

public class AvatarView extends ConstraintLayout {

  private AvatarImageView     avatarImage;
  private ImageView           seenRecentlyIndicator;

  public AvatarView(Context context) {
    super(context);
    init();
  }

  public AvatarView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public AvatarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    inflate(getContext(), R.layout.avatar_view, this);

    avatarImage             = findViewById(R.id.avatar_image);
    seenRecentlyIndicator   = findViewById(R.id.status_indicator);
  }

  public void setAvatar(@NonNull GlideRequests requestManager, @Nullable Recipient recipient, boolean quickContactEnabled) {
    avatarImage.setAvatar(requestManager, recipient, quickContactEnabled);
  }

  public void setAvatarClickListener(OnClickListener listener) {
    avatarImage.setOnClickListener(listener);
  }

  public void setAvatarLongClickListener(OnLongClickListener listener) {
    avatarImage.setOnLongClickListener(listener);
  }

  public void setSeenRecently(boolean enabled) {
    seenRecentlyIndicator.setVisibility(enabled? View.VISIBLE : View.GONE);
  }

  public void setConnectivity(int connectivity) {
      final int id;
      if (connectivity >= DcContext.DC_CONNECTIVITY_WORKING) {
          id = R.drawable.ic_circle_status_online;
      } else if (connectivity >= DcContext.DC_CONNECTIVITY_CONNECTING) {
          id = R.drawable.ic_circle_status_connecting;
      } else {
          id = R.drawable.ic_circle_status_offline;
      }
      seenRecentlyIndicator.setImageDrawable(ContextCompat.getDrawable(getContext(), id));
      seenRecentlyIndicator.setVisibility(View.VISIBLE);
  }

  public void clear(@NonNull GlideRequests glideRequests) {
    avatarImage.clear(glideRequests);
  }

}
