# RealtimeWeatherApp

An Android weather lookup app written in Kotlin. Enter a city to request current conditions from WeatherAPI; the UI displays loading, result, and error states.

## Tech and structure

- Kotlin Android app with a `WeatherViewModel` and a Compose weather screen
- Retrofit for the WeatherAPI request
- `NetworkResponse` state for loading, success, and failure

The main code is under `app/src/main/java/com/abpvt/realtimeweather1/`.

## Build

Open the repository in Android Studio, let Gradle sync, and run the `app` configuration on an emulator or device. From a terminal, use `./gradlew assembleDebug` (or `gradlew.bat assembleDebug` on Windows). The checked-in unit test is the Android template test; it does not cover the weather request flow.

## Credential warning

The current source contains a WeatherAPI key in `api/Constant.kt`. Treat that key as exposed: rotate or revoke it in the provider dashboard before reuse, then move the replacement to a non-committed configuration mechanism. Removing it from the latest source commit alone does not remove it from Git history. Do not reuse the checked-in key in production.
