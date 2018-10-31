# Gmail Find and Extract Messages (terminal application only)

*Description: A simple terminal app to find messages in your Gmail, extract message body, and saves it as HTML or text files.*

## Intro

Originally intended this to grab all of my emails from Grab receipts and Sunlife MF NAVPS reports, then save it to files. In the near future, will write another utility app to parse and collate all the data to Google Spreadsheets so I can keep track of my fare spendings and MF activities.

## Searching for Messages
Search syntax follows Gmail's advanced search. Read the appropriate Useful References section below for explanation on how it works.
 
The search string is in the **settings.json** file under the key *query*.
 
Example:
> **"in:inbox after:2018/10/25 from:sender@example.com 'Foo bars'"**

#### Requirements
* Java 1.7 or greater
* Gradle 2.3 or greater *(Or you use Maven)*
* A Google account with Gmail
* credentials.json
* settings.json *(Included in src/main/resources/)*

#### Steps To Run
1. Create a new Google console project.
2. Enable the Gmail API.
3. Download the project credentials file *(Create one if you haven't yet)*.
4. Copy the credentials.json file from step #3 to src/main/resources/ directory.
5. Open a terminal. 
6. Change to the appropriate directory.
7. Run with command 'gradle -q run'.

### Note
> The first time you run the app, it will prompt you to authorize access.

> It will attempt to open a new window or tab in your default browser. If this fails, copy the URL from the console and manually open it in your browser.

> If you are not already logged into your Google account, you will be prompted to log in. If you are logged into multiple Google accounts, you will be asked to select one account to use for the authorization.

> Click the Accept button.

> The sample will proceed automatically, and you may close the browser window/tab.

#### Gradle Setup
```gradle
repositories {
	mavenCentral();
}

dependencies {
	compile 'com.google.api-client:google-api-client:1.25.0'
	compile 'com.google.oauth-client:google-oauth-client-jetty:1.26.0'
	compile 'com.google.apis:google-api-services-gmail:v1-rev96-1.25.0'
	compile 'com.google.code.gson:gson:2.8.5'
}
```
#### Caveats
* Will not download/resolve in-line images in the message body.
* Ignores attachment files.
* Handles only mimetypes that are text/plain, text/html, multipart/alternative, multipart/mixed, multipart/related. 

#### Useful References
* [Advanced Search Syntax for Messages](https://support.google.com/mail/answer/7190?hl=en) 
* [Create Google Project](https://cloud.google.com/resource-manager/docs/creating-managing-projects)
* [Gmail API](https://developers.google.com/gmail/api/)
* [Test Gmail API with Explorer](https://developers.google.com/apis-explorer/?hl=en_US#p/gmail/v1/)
* [List of Auth Scopes](https://developers.google.com/gmail/api/auth/scopes/)
* [Java Quickstart guide for Gmail API](https://developers.google.com/gmail/api/quickstart/java)

#### Todo
1. Save downloaded message/s in its own directory by sender email
2. Group message/s in its own directory according to date
