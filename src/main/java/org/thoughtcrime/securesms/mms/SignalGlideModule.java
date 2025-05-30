package org.thoughtcrime.securesms.mms;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.annotation.NonNull;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.model.UnitModelLoader;
import com.bumptech.glide.module.AppGlideModule;
import com.github.penfeizhou.animation.webp.decode.WebPDecoder;

import com.caverock.androidsvg.SVG;

import org.thoughtcrime.securesms.contacts.avatars.ContactPhoto;
import org.thoughtcrime.securesms.glide.ContactPhotoLoader;
import org.thoughtcrime.securesms.glide.lottie.LottieDecoder;
import org.thoughtcrime.securesms.glide.lottie.LottieDrawableTranscoder;
import org.thoughtcrime.securesms.glide.svg.SvgDecoder;
import org.thoughtcrime.securesms.glide.svg.SvgDrawableTranscoder;
import org.thoughtcrime.securesms.glide.webp.WebpDrawableTranscoder;
import org.thoughtcrime.securesms.glide.webp.WebpLoader;
import org.thoughtcrime.securesms.mms.DecryptableStreamUriLoader.DecryptableUri;

import java.io.File;
import java.io.InputStream;

@GlideModule
public class SignalGlideModule extends AppGlideModule {

  @Override
  public boolean isManifestParsingEnabled() {
    return false;
  }

  @Override
  public void applyOptions(@NonNull Context context, GlideBuilder builder) {
    builder.setLogLevel(Log.ERROR);
//    builder.setDiskCache(new NoopDiskCacheFactory());
  }

  @Override
  public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
    //AttachmentSecret attachmentSecret = AttachmentSecretProvider.getInstance(context).getOrCreateAttachmentSecret();
    //byte[]           secret           = attachmentSecret.getModernKey();

    registry.prepend(File.class, File.class, UnitModelLoader.Factory.getInstance());
    //registry.prepend(InputStream.class, new EncryptedCacheEncoder(secret, glide.getArrayPool()));
    //registry.prepend(File.class, Bitmap.class, new EncryptedBitmapCacheDecoder(secret, new StreamBitmapDecoder(new Downsampler(registry.getImageHeaderParsers(), context.getResources().getDisplayMetrics(), glide.getBitmapPool(), glide.getArrayPool()), glide.getArrayPool())));
    //registry.prepend(File.class, GifDrawable.class, new EncryptedGifCacheDecoder(secret, new StreamGifDecoder(registry.getImageHeaderParsers(), new ByteBufferGifDecoder(context, registry.getImageHeaderParsers(), glide.getBitmapPool(), glide.getArrayPool()), glide.getArrayPool())));

    //registry.prepend(Bitmap.class, new EncryptedBitmapResourceEncoder(secret));
    //registry.prepend(GifDrawable.class, new EncryptedGifDrawableResourceEncoder(secret));

    registry.append(ContactPhoto.class, InputStream.class, new ContactPhotoLoader.Factory(context));
    registry.append(DecryptableUri.class, InputStream.class, new DecryptableStreamUriLoader.Factory(context));
    //registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());

    registry
        .prepend(InputStream.class, WebPDecoder.class, new WebpLoader())
        .register(WebPDecoder.class, Drawable.class, new WebpDrawableTranscoder());

    registry
        .register(LottieComposition.class, LottieDrawable.class, new LottieDrawableTranscoder())
        .append(InputStream.class, LottieComposition.class, new LottieDecoder());

    registry
        .register(SVG.class, PictureDrawable.class, new SvgDrawableTranscoder())
        .append(InputStream.class, SVG.class, new SvgDecoder());
  }
}
