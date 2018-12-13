# CatalogIt App
[![CircleCI](https://circleci.com/gh/gabeira/CatalogIt.svg?style=svg)](https://circleci.com/gh/gabeira/CatalogIt)

![N|Solid](https://raw.githubusercontent.com/gabeira/CatalogIt/master/app/src/main/res/mipmap-mdpi/ic_launcher.png)

This is an Android App to show a Catalogue of Media Content details.

  - Kotlin Development
  - MVVM Architecture
  - Coroutines
  - Retrofit Http Client

## Configuration

This Project was developed using [Android Studio](https://developer.android.com/studio/)

To Download the code from this Repository you can use Android Studio or command line, running:
```sh
git clone https://github.com/gabeira/CatalogIt
```
To Build the Project, you can use Android Studio or from command line, just run:
```sh
./gradlew build
```
To install debug app from command line, use:
```sh
./adb install /app/build/outputs/apk/app-debug.apk
```

## External Libs Reference

- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
- [Kotlin Coroutine Adapter](https://github.com/JakeWharton/retrofit2-kotlin-coroutines-adapter)
- [Retrofit](https://square.github.io/retrofit/)

## Tests

There is some small tests done, but essential for the functionalities, you can run on Android Studio or from the command line,
to run the Unit Tests just use:
```sh
./gradlew test
```

Also there is some Connected Android Tests, but this requires to have a device or emulator connected:
```sh
./gradlew connectedAndroidTest
```

Ps. If I have more time would do more Instrumented Tests and add Unit Tests for the Architecture components.