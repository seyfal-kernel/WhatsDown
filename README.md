## DeltaLab Android Client

DeltaLab is a [Delta Chat](https://delta.chat/) client for Android.

[<img src="store/get-it-on-IzzyOnDroid.png"
     alt="Get it on IzzyOnDroid"
     height="48">](https://apt.izzysoft.de/fdroid/index/apk/chat.delta.lite)
[<img src="store/get-it-on-apklis.png"
     alt="Disponible en Apklis"
     height="48">](https://www.apklis.cu/application/chat.delta.lite)
[<img src="store/get-it-on-github.png"
     alt="Get it on GitHub"
     height="48">](https://github.com/adbenitez/deltalab-android/releases/latest)


<img alt="Screenshot Chat List" src="fastlane/metadata/android/en-US/images/phoneScreenshots/02.png" width="298" /> <img alt="Screenshot Chat View" src="fastlane/metadata/android/en-US/images/phoneScreenshots/04.jpg" width="298" />

# WebXDC

DeltaLab has some extended support for WebXDC apps:

- `window.webxdc.deltalab` is a string with the DeltaLab version and can be used by app developers
  to detect when they can use the DeltaLab-specific features.
- `sendToChat()`: extra property `subject` can be set to a text string to set message/email's subject.
- `sendToChat()`: extra property `html` can be set to a string of html markup to set the HTML part of the email/message.
- `sendToChat()`: the file object parameter also accepts a `type` field that can be one of:
  * `"sticker"`
  * `"image"`
  * `"audio"`
  * `"video"`
  * `"file"` (default if `type` field is not present)
- Inside apps, clicking external links is supported, ex. to open in browser, so you can include links to your website or donation pages.
- `manifest.toml` field: `orientation`, if you set it to `"landscape"` your app will be launched in landscape mode.

# Translations

Android metadata and changelogs are translated using [Weblate](https://hosted.weblate.org/projects/deltachat/android-metadata/).

<a href="https://hosted.weblate.org/engage/deltachat/">
<img src="https://hosted.weblate.org/widget/deltachat/android-metadata/svg-badge.svg" alt="Translation status" />
</a>

# Credits

DeltaLab is based on the [official Delta Chat client](https://github.com/deltachat/deltachat-android) with some small improvements.

DeltaLab uses a [modified](https://github.com/adbenitez/deltalab-core) version of the [Delta Chat Core Library](https://github.com/deltachat/deltachat-core-rust).

# License

Licensed GPLv3+, see the LICENSE file for details.

Copyright © 2022 DeltaLab contributors.
