# Privacy Policy ArcaneChat Apps

**This is the privacy policy for the ArcaneChat (Delta Chat client) apps.**

## Summary

ArcaneChat is an e-mail app that follows a strict privacy-by-design approach. All communication data remains on the end devices or on the e-mail servers selected by the user.

<table>
    <thead>
        <tr>
            <th>Requirement</th>
            <th>Implementation</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Confidentiality</td>
            <td>
<b>End-to-end encryption</b> by implementing <a href="https://autocrypt.org">Autocrypt</a> and <a href="https://securejoin.delta.chat/en/latest/new.html">Verified groups</a>. User-friendly end-to-end encryption means that the requirements of the GDPR are already implemented at the technical level, <b>Privacy by Design</b>.
            </td>
        </tr>
        <tr>
            <td>Data minimization</td>
            <td><b>No upload</b> of contact lists from your phone.</td>
        </tr>
        <tr>
            <td>Data avoidance</td>
            <td><b>No ArcaneChat servers</b>, no processing of personal data. All data remains with your e-mail provider.
            </td>
        </tr>
        <tr>
            <td>Legal basis</td>
            <td>
There is no need to obtain consent for address book matching, <a href="https://gdpr-info.eu/art-7-gdpr/">Art.7 GDPR</a>, as no address book data is transferred. Therefore, <b>no additional legal basis</b> is required.
            </td>
        </tr>
        <tr>
            <td>Data to third parties</td>
            <td>We only receive the <b>token for the push notification</b> and forward it to the provider of your operating system. If you don't want to receive push notifications, we won't forward a token.</td>
        </tr>
        <tr>
            <td>Data from third parties</td>
            <td>
Easy implementation in companies: ArcaneChat does not process personal data on behalf of the controller and therefore <b>does not require any instruction or data processing agreement</b>. Easy connection with the own corporate e-mail server.
            </td>
        </tr>
        <tr>
            <td>
DPIA
            </td>
            <td>
No <b>data protection impact assessment</b> needs to be carried out for ArcaneChat <a href="https://gdpr-info.eu/art-35-gdpr/">Art.35 GDPR</a>, as no specific additional data is processed beyond the e-mail messenger process.

The risk to the rights and freedoms of natural persons is limited to the internal company data processing of the e-mail communication and that of the e-mail providers.
            </td>
        </tr>
        <tr>
            <td>
Documentation
            </td>
            <td>
Inclusion of the measures implemented by ArcaneChat in the <b>record of processing activities</b> may have a positive impact on possible evidence, <a href="https://gdpr-info.eu/art-30-gdpr/">Art.30 GDPR</a> as well as certification processes, <a href="https://gdpr-info.eu/art-25-gdpr/">Art.25 (4) GDPR</a>,<a href="https://gdpr-info.eu/art-42-gdpr/">Art.42 GDPR</a>.

The <b>documentation of processing activities</b> related to Messenger communication is omitted and shifts only to the record of processing activities of your e-mail provider, <a href="https://gdpr-info.eu/art-30-gdpr/">Art.30(2) GDPR</a>.
            </td>
        </tr>
    </tbody>
</table>

## Detailed data protection information

### 1. Name and contact information of the data controller

Responsible for the processing of your personal data is 
```
    Asiel Diaz Benitez
    Reichsgrafen Str. 20
    79102 Freiburg
    Germany
```
E-mail: [adbenitez@duck.com](mailto:adbenitez@duck.com)

### 2. Processing when using the ArcaneChat e-mail messenger

All data is stored locally on your device or with your e-mail provider. Neither we nor ArcaneChat developers have any possibility to access the end user's data, as all communication and data processing takes place on the end user's device without ArcaneChat servers being involved. Only for the optional Push Notification (see 2.2) the Push Notification Token is processed by Delta Chat servers without them having any knowledge about the messages themselves, not even in encrypted form.

#### 2.1 Sign up with your e-mail provider

ArcaneChat communication works through your e-mail account. In order to establish the connection, the app needs the access data. These are only stored locally on your end device:

- e-mail address
- your password

The legal basis for the processing is [Art.6 (1) lit.b GDPR](https://gdpr-info.eu/art-6-gdpr/), as you have a usage contract with us by using our services. 

Security procedures are in place to protect the confidentiality of the data:
Only the bare data needed to fulfil the user request of login are stored,
encryption takes place as well as local sandboxing,
see 2.3 for further details.

#### 2.2 Heartbeat push notification

Push notifications can be sent to the user's phone at regular intervals to enable receipt of messages while the ArcaneChat app is not currently active. Current messages can then be retrieved on the end device. The token is only created if the user wants reliable receipt of messages even when the app is not active. 

In order to use push notifications, a unique identifier or token (Push Notification Token) is created after the app is downloaded and installed. This token allows Delta Chat servers to send notifications to the user's device. The token is generated and provided by the provider. The token is then stored on our systems and sent to the device at regular intervals to cause the app to retrieve new messages from the e-mail provider. Our systems have no knowledge of whether a message arrives or who may have sent a message.

The aforementioned data will only be processed with your consent, in accordance with [Art.6 (1) lit.a GDPR](https://gdpr-info.eu/art-6-gdpr/).

#### 2.3 Data in the app

The ArcaneChat app works in a data-saving way. All relevant data is stored exclusively on the respective end devices:

```
- chat histories (text messages, voice messages, media, ...).
- contacts
- settings 
```
ArcaneChat servers have no access to this data, not even in encrypted form, as data processing takes place only on the end devices.

#### 2.4 App permissions

Within the app, you can enter, manage, and edit various information, tasks, and activities. The app also requires the following permissions:

<table>
    <thead>
        <tr>
            <th>Permission</th>
            <th>Reason</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Internet access</td>
            <td>This is needed to send the messages to the communication partner. </td>
        </tr>
        <tr>
            <td>Camera access</td>
            <td>
This is needed for you to take photos and send them via the app. In addition, the camera access allows you to scan QR codes.
            </td>
        </tr>
        <tr>
            <td>Microphone access (optional)</td>
            <td>
This permission allows you to send voice messages.
            </td>
        </tr>
        <tr>
            <td>Location access (optional)</td>
            <td>
This is needed if you want to share your location with a communication partner.
            </td>
        </tr>
        <tr>
            <td>Background location access (optional)</td>
            <td>
This will be needed if you want to share your location over a certain period of time.
            </td>
        </tr>
        <tr>
            <td>Contact access (optional)</td>
            <td>
This allows you to load and save contacts from your phone book into the app. The contact data is stored locally in the app and not forwarded and stored on ArcaneChat server.
            </td>
        </tr>
        <tr>
            <td>
Storage (optional)
            </td>
            <td>You can save images and files from the app to your device.</td>
        </tr>
    </tbody>
</table>

The processing and use of the above permissions are performed to provide the service. The internet access is necessary for the use, therefore the legal basis of the processing is [Art.6 (1) lit.a GDPR](https://gdpr-info.eu/art-6-gdpr/), as you have a usage contract with us by using our services.

The optional permissions only take place based on your consent according to [Art.6 (1) lit.a GDPR](https://gdpr-info.eu/art-6-gdpr/) and can also be reduced to individual services, e.g. only internet usage to send messages without pictures or location data. ArcaneChat does not receive any access to this data.

### 3. Platform dependent processing

Certain information is already processed automatically as soon as you use the app. We have listed below which personal data is processed exactly:

When you download the app, certain required information is transmitted to the app store you use ( e.g. Google Play or Apple App Store), in particular the username, the email address, the customer number of your account, the time of the download, payment information as well as the individual device identification number may be processed. The processing of this data is carried out exclusively by the respective app store and is beyond our control.

For the rest, we refer to the data protection policies of the respective app store providers or responsible parties of the operating systems. We do not collect and/or process any other data.

Your personal data will not be transferred to third parties for purposes other than those listed below.

# 4. Rights of the data subject

As a data subject of a processing of personal data, you have the right to 

1. request information about your personal data processed by us in accordance with [Art.15 GDPR](https://gdpr-info.eu/art-15-gdpr/). In particular, you can request information about the processing purposes, the category of personal data, the categories of recipients to whom your data have been or will be disclosed, the planned storage period, the existence of a right to rectification, erasure, restriction of processing or objection, the existence of a right of complaint, the origin of your data if it has not been collected by us, as well as the existence of automated decision-making including profiling and, if applicable, meaningful information about its details;
2. in accordance with [Art.16 GDPR](https://gdpr-info.eu/art-16-gdpr/) of the GDPR, immediately request the correction of inaccurate or incomplete personal data stored by us;
3. pursuant to [Art.17 GDPR](https://gdpr-info.eu/art-17-gdpr/) of the GDPR, to request the erasure of your personal data stored by us, unless the processing is necessary for the exercise of the right to freedom of expression and information, for compliance with a legal obligation, for reasons of public interest, or for the establishment, exercise or defence of legal claims;
4. pursuant to [Art.18 GDPR](https://gdpr-info.eu/art-18-gdpr/), to request the restriction of the processing of your personal data, insofar as the accuracy of the data is disputed by you, the processing is unlawful, but you object to its erasure and we no longer require the data, but you need it for the assertion, exercise or defence of legal claims or you have objected to the processing pursuant to [Art.21 GDPR](https://gdpr-info.eu/art-21-gdpr/);
5. pursuant to [Art.20 GDPR](https://gdpr-info.eu/art-20-gdpr/), to receive your personal data that you have provided to us in a structured, common and machine-readable format or to request that it be transferred to another controller;
6. in accordance with [Art.7 (3) GDPR](https://gdpr-info.eu/art-7-gdpr/), to revoke your consent given to us at any time. This has the consequence that we may no longer continue the data processing based on this consent in the future; and
7. complain to a supervisory authority in accordance with [Art.77 GDPR](https://gdpr-info.eu/art-77-gdpr/) of the GDPR. As a rule, you can contact the supervisory authority of your usual place of residence or workplace.


# 5. Up-to-dateness and modification of this data protection declaration

This data protection declaration is valid as of January 2025. Due to the further development of our website and offers or due to changed legal or official requirements, it may become necessary to revise this data protection declaration from time to time.
