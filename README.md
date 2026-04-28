# SmartTrack Expense Manager

## Description
SmartTrack is a modern, offline-first personal finance application. It allows users to track their daily expenses through manual entry or by automatically scanning receipts using device-local AI (Google ML Kit). All data is saved locally via Room for instant access and backed up to Firebase Firestore.

## Complexity Level
**Advanced**
* Architecture: Clean Architecture (MVVM) + Repository Pattern + StateFlow.
* Dependency Injection: Hilt.
* UI: 100% Jetpack Compose.
* Features: ML Kit (OCR text recognition), Live Currency API Integration (Retrofit), Local Database (Room), and Cloud Sync (Firebase).

## Build Instructions & API Key Setup
To build and run this application locally, you must provide your own Currency API key (e.g., from ExchangeRate-API).

1. Clone the repository:
   `git clone https://github.com/<YOUR_USERNAME>/SmartTrack.git`
2. Open the project in **Android Studio**.
3. In the root directory of the project, open (or create) the `local.properties` file.
4. Add your API key to the file like this:
   `CURRENCY_API_KEY=your_actual_api_key_here`
5. Sync the Gradle project and press **Run**.

## Screenshots
> *(Screenshots will be added here as UI development progresses in Class 4)*