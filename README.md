<div align="center">

# WGet

A clean, native Android utility for fetching HTTP/HTTPS web content and managing file downloads.

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Platform](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg)](https://developer.android.com)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)

</div>

## Overview

WGet provides a graphical interface on Android to retrieve source code, inspect HTTP responses, and trigger downloads using the Android DownloadManager service.

This is a legacy project originally developed on a mobile device using AIDE (Android IDE), which has now been updated and synchronized with modern Android Studio and Gradle build tools.

## Features

- Fetch raw HTTP/HTTPS responses from any valid URL.
- Inspect headers and web page markup directly on device.
- Copy fetched responses to clipboard with a single click.
- Download target files directly to device storage.
- Crash reporting and error log diagnostics.

## Screenshots

<p align="center">
  <img src="screenshots/1.jpg" width="30%" alt="Splash Screen" />
  <img src="screenshots/2.jpg" width="30%" alt="Main Interface" />
  <img src="screenshots/3.jpg" width="30%" alt="Fetched Response" />
</p>
<p align="center">
  <img src="screenshots/4.jpg" width="30%" alt="Response Details" />
  <img src="screenshots/5.jpg" width="30%" alt="Action Menu" />
  <img src="screenshots/6.jpg" width="30%" alt="Crash Handler" />
</p>

## Requirements

- Android 5.0 (API Level 21) or higher
- JDK 17 or higher for building from source

## Building from Source

Clone the repository and build using the Gradle wrapper:

```bash
git clone https://github.com/farhaanaliii/WGet.git
cd WGet
./gradlew assembleDebug
```

The compiled APK will be generated under `app/build/outputs/apk/debug/`.

## Author

Farhan Ali
- GitHub: [@farhaanaliii](https://github.com/farhaanaliii)
- Email: i.farhanali.dev@gmail.com

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.