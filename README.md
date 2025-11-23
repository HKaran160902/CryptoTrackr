# CryptoTrackr

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0-blue.svg)](https://kotlinlang.org)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-Material3-purple.svg)](https://developer.android.com/jetpack/compose)
[![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg)](https://android-arsenal.com/api?level=24)

A modern Android cryptocurrency tracking application built with Jetpack Compose and Material Design 3. Track real-time crypto prices, manage your watchlist, set price alerts, and stay updated with the latest market trends.

**Powered by [CoinGecko API](https://www.coingecko.com/en/api)** for reliable, real-time cryptocurrency data.

---

## Features

- **Real-time Market Data** - Browse top cryptocurrencies by market cap with live price updates from CoinGecko API
- **Watchlist Management** - Save your favorite coins for quick access
- **Price Alerts** - Set custom price alerts (above/below threshold) with push notifications
- **Price History Charts** - View historical price data across multiple timeframes
- **Search** - Find any cryptocurrency with real-time search results
- **Trending Coins** - Discover trending cryptocurrencies
- **Theme Support** - Dynamic colors with Dark/Light theme support

---

## Tech Stack

| Category | Technology |
|----------|------------|
| **Language** | Kotlin 2.0 |
| **UI Framework** | Jetpack Compose |
| **Design System** | Material Design 3 |
| **Architecture** | MVVM + Clean Architecture |
| **Dependency Injection** | Hilt 2.51 |
| **Networking** | Retrofit 2.9 + OkHttp 4.12 |
| **Local Database** | Room 2.6 |
| **Preferences** | DataStore |
| **Async** | Kotlin Coroutines + StateFlow |
| **Background Tasks** | WorkManager |
| **Image Loading** | Coil |
| **Navigation** | Jetpack Navigation Compose |
| **API** | CoinGecko API v3 (Free, no authentication required) |

---

## Architecture

The app follows **MVVM + Clean Architecture** pattern with three distinct layers:

```
┌─────────────────────────────────────────────────────────┐
│                      UI Layer                           │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐     │
│  │   Screens   │  │  ViewModels │  │  UI State   │     │
│  │  (Compose)  │  │             │  │             │     │
│  └─────────────┘  └─────────────┘  └─────────────┘     │
└─────────────────────────────────────────────────────────┘
                          │
                          ▼
┌─────────────────────────────────────────────────────────┐
│                    Domain Layer                         │
│  ┌─────────────────────┐  ┌─────────────────────┐      │
│  │    Models           │  │  Repository         │      │
│  │  (Data Classes)     │  │  (Interfaces)       │      │
│  └─────────────────────┘  └─────────────────────┘      │
└─────────────────────────────────────────────────────────┘
                          │
                          ▼
┌─────────────────────────────────────────────────────────┐
│                     Data Layer                          │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐  ┌────────┐  │
│  │  Remote  │  │  Local   │  │  Mapper  │  │  Repo  │  │
│  │  (API)   │  │  (Room)  │  │          │  │  Impl  │  │
│  └──────────┘  └──────────┘  └──────────┘  └────────┘  │
└─────────────────────────────────────────────────────────┘
```

**Key Patterns:**
- **Resource Wrapper** - All async operations return `Resource<T>` (Loading/Success/Error)
- **Cache Strategy** - Cache-first with 5-minute expiry, network refresh, fallback on failure
- **Unidirectional Data Flow** - StateFlow for reactive UI updates

---

## Project Structure

```
CryptoTrackr/
├── app/
│   └── src/main/java/com/hari/pdd/cryptotrackr/
│       ├── CryptoTrackrApp.kt          # Application class (Hilt, WorkManager)
│       ├── MainActivity.kt
│       ├── data/
│       │   ├── remote/
│       │   │   ├── api/                # Retrofit API interface
│       │   │   └── dto/                # Data Transfer Objects
│       │   ├── local/
│       │   │   ├── database/           # Room database & DAOs
│       │   │   ├── entity/             # Database entities
│       │   │   └── preferences/        # DataStore preferences
│       │   ├── mapper/                 # DTO ↔ Entity ↔ Domain mappers
│       │   └── repository/             # Repository implementations
│       ├── domain/
│       │   ├── model/                  # Domain models
│       │   └── repository/             # Repository interfaces
│       ├── ui/
│       │   ├── screens/                # Screen composables + ViewModels
│       │   ├── components/             # Reusable UI components
│       │   ├── navigation/             # Navigation graph & routes
│       │   └── theme/                  # Material 3 theme
│       ├── di/                         # Hilt dependency modules
│       ├── worker/                     # WorkManager workers
│       └── util/                       # Utilities & extensions
├── .github/
│   └── workflows/
│       └── android-release.yml         # CI/CD pipeline
├── gradle/
│   └── libs.versions.toml              # Version catalog
└── build.gradle.kts
```

---

## Setup Instructions

### Prerequisites

- **Android Studio** Hedgehog (2023.1.1) or later
- **JDK 17**
- **Android SDK** with API level 34
- **Git**

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/HKaran160902/CryptoTrackr.git
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select `File > Open`
   - Navigate to the cloned `CryptoTrackr` folder

3. **Sync Gradle**
   - Android Studio will automatically prompt to sync
   - Or manually: `File > Sync Project with Gradle Files`

4. **Run the app**
   - Select a device/emulator (API 24+)
   - Click `Run` or press `Shift + F10`

### Build Commands

```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease

# Clean build
./gradlew clean build
```

---

## CI/CD Pipeline

The project uses **GitHub Actions** for continuous integration and delivery.

### Workflow Overview

| Trigger | Action |
|---------|--------|
| Push to `main` | Build signed release APK → Create GitHub Release |

### Pipeline Steps

1. Checkout code
2. Setup JDK 17 (Temurin)
3. Decode keystore from secrets
4. Build signed release APK
5. Upload artifact to GitHub Release

### Required Secrets

Configure these in your GitHub repository settings (`Settings > Secrets and variables > Actions`):

| Secret | Description |
|--------|-------------|
| `KEYSTORE_BASE64` | Base64 encoded keystore file |
| `KEYSTORE_PASSWORD` | Keystore password |
| `KEY_ALIAS` | Key alias name |
| `KEY_PASSWORD` | Key password |

---

## API Reference

This app uses the **CoinGecko API v3** (free tier, no authentication required).

**Base URL:** `https://api.coingecko.com/api/v3/`

**Endpoints used:**
- `/coins/markets` - Get market data for coins
- `/coins/{id}` - Get detailed coin information
- `/coins/{id}/market_chart` - Get historical price data
- `/search` - Search for coins
- `/search/trending` - Get trending coins

For more details, visit the [CoinGecko API Documentation](https://www.coingecko.com/en/api/documentation).

---

## License

```
MIT License

Copyright (c) 2024 Harikaran

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
