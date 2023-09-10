## DeltaLab Android Client

DeltaLab is a [Delta Chat](https://delta.chat/) client for Android.

[<img src="https://gitlab.com/IzzyOnDroid/repo/-/raw/master/assets/IzzyOnDroid.png"
     alt="Get it on IzzyOnDroid"
     height="80">](https://apt.izzysoft.de/fdroid/index/apk/chat.delta.lite)
[<img src="https://delta.chat/assets/badges/get-it-on-apklis.png"
     alt="Disponible en Apklis"
     height="70">](https://www.apklis.cu/application/chat.delta.lite)

Or get the latest APK from the [Releases section](https://github.com/adbenitez/deltalab-android/releases/).

<img alt="Screenshot Chat List" src="fastlane/metadata/android/en-US/images/phoneScreenshots/02.jpg" width="298" /> <img alt="Screenshot Chat View" src="fastlane/metadata/android/en-US/images/phoneScreenshots/04.jpg" width="298" />

# WebXDC

DeltaLab has some extended support for WebXDC apps:

- `window.webxdc.deltalab` is `true` and can be used by app developers to detect when they can use the DeltaLab-specific features
- `sendToChat()`: the file object parameter also accepts a `type` field that can be one of:
  * `"sticker"`
  * `"image"`
  * `"audio"`
  * `"video"`
  * `"file"` (default if `type` field is not present)
- Inside apps, clicking external links is supported, ex. to open in browser, so you can include links to your website or donation pages.
- `manifest.toml` field: `orientation`, if you set it to `"landscape"` your app will be launched in full screen and landscape mode

# Credits

DeltaLab is based on the [official Delta Chat client](https://github.com/deltachat/deltachat-android) with some small improvements.

DeltaLab uses a [modified](https://github.com/adbenitez/deltalab-core) version of the [Delta Chat Core Library](https://github.com/deltachat/deltachat-core-rust).

# License

Licensed GPLv3+, see the LICENSE file for details.

Copyright © 2022 DeltaLab contributors.
