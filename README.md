## ArcaneChat Android Client

A [Delta Chat](https://delta.chat/) client for Android.

[<img src="store/get-it-on-fdroid.png" alt="Get it on F-Droid" height="48">](https://f-droid.org/packages/chat.delta.lite)
[<img src="store/get-it-on-IzzyOnDroid.png" alt="Get it on IzzyOnDroid" height="48">](https://apt.izzysoft.de/fdroid/index/apk/chat.delta.lite)
[<img src="store/get-it-on-apklis.png" alt="Disponible en Apklis" height="48">](https://www.apklis.cu/application/chat.delta.lite)
[<img src="store/get-it-on-github.png" alt="Get it on GitHub" height="48">](https://github.com/ArcaneChat/android/releases/latest/download/ArcaneChat-gplay.apk)


<img alt="Screenshot" src="fastlane/metadata/android/en-US/images/phoneScreenshots/1.png" width="298" /> <img alt="Screenshot" src="fastlane/metadata/android/en-US/images/phoneScreenshots/2.png" width="298" />

# WebXDC

This app has some extended support for WebXDC apps:

- `window.webxdc.arcanechat` a string with the ArcaneChat version and can be used by app developers
  to detect when they can use the ArcaneChat-specific features.
- `window.webxdc.isCommunity` true if the current account is a community account
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

# Credits

This app is based on the [official Delta Chat client](https://github.com/deltachat/deltachat-android) with several improvements.

This app uses a [modified](https://github.com/ArcaneChat/core) version of the [Delta Chat Core Library](https://github.com/deltachat/deltachat-core-rust).
