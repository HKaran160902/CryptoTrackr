# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Build debug APK
gradlew.bat assembleDebug

# Build release bundle
gradlew.bat bundleRelease

# Clean and rebuild
gradlew.bat clean build

# View dependency tree
gradlew.bat dependencies
```

**Note:** Requires `local.properties` with `sdk.dir` pointing to Android SDK location.

## Architecture

**Pattern:** MVVM + Clean Architecture

```
┌─────────────────────────────────────────────────────────┐
│  UI Layer (Compose)                                     │
│  screens/ → ViewModels + UiState + Composables          │
│  components/ → Reusable UI (cards, buttons, charts)     │
├─────────────────────────────────────────────────────────┤
│  Domain Layer                                           │
│  model/ → Pure Kotlin data classes                      │
│  repository/ → Abstract interfaces                      │
├─────────────────────────────────────────────────────────┤
│  Data Layer                                             │
│  remote/api/ → Retrofit (CoinGecko)                     │
│  local/database/ → Room                                 │
│  local/preferences/ → DataStore                         │
│  repository/ → Implementations with caching             │
└─────────────────────────────────────────────────────────┘
```

## Package Structure

- `data/remote/` - CoinGeckoApi interface, DTOs
- `data/local/` - Room database, DAOs, entities, DataStore preferences
- `data/mapper/` - DTO ↔ Entity ↔ Domain model conversions
- `data/repository/` - Repository implementations with cache-first strategy
- `domain/model/` - Business models (Coin, CoinDetail, ChartData, Holding, PriceAlert)
- `domain/repository/` - Repository contracts
- `ui/screens/` - 6 screens each with Screen/ViewModel/UiState
- `ui/components/` - Reusable composables
- `ui/navigation/` - NavRoutes, NavGraph, NavAnimations
- `di/` - Hilt modules (Network, Database, Repository)
- `worker/` - PriceAlertWorker for background monitoring

## API Configuration

**Base URL:** `https://api.coingecko.com/api/v3/`

| Endpoint | Purpose |
|----------|---------|
| `/coins/markets` | Top coins by market cap |
| `/coins/{id}` | Detailed coin info |
| `/coins/{id}/market_chart` | Price history |
| `/search` | Search coins |
| `/search/trending` | Trending coins |

## Database Schema

**Room Database:** `CryptoDatabase`

| Table | Primary Key | Purpose |
|-------|-------------|---------|
| `coins` | `id: String` | Cached coin data |
| `watchlist` | `coinId: String` | User's watchlist |
| `holdings` | `id: Long` | Portfolio holdings |
| `alerts` | `id: Long` | Price alerts |

## Navigation

**Bottom Nav:** Home → Watchlist → Alerts → Profile

**Detail Screens:** CoinDetail (arg: coinId), Settings, AddHolding, AddAlert

**Animations:**
- Bottom nav: Fade transitions
- Detail screens: Slide left/right with fade

## Key Patterns

**Resource Wrapper:** All async operations return `Resource<T>` (Loading/Success/Error)

**Caching:** 5-minute cache duration, cache-first with network refresh, fallback to cache on failure

**State Management:** MutableStateFlow in ViewModels, StateFlow exposed to UI

**Search:** 300ms debounce, searches local cache first then API

## Dependency Injection

**Framework:** Hilt

- `@HiltAndroidApp` on Application class
- `@AndroidEntryPoint` on Activities/Fragments
- `@HiltViewModel` on ViewModels
- Modules in `di/`: NetworkModule, DatabaseModule, RepositoryModule

## Background Work

**PriceAlertWorker:** Uses WorkManager for periodic price checks and alert notifications.

## Database Notes

- No Room migrations are used
- Schema changes require manual SQL execution on the database
- Provide raw SQL queries for any schema modifications

## SDK Requirements

- minSdk: 24, targetSdk: 34, compileSdk: 34
- Java/Kotlin target: 17
- Gradle: 8.5.0 (AGP), Kotlin: 2.0.0
