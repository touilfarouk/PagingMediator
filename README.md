# Deliverer Search App

A clean, modern Android application for searching and displaying deliverer information with offline
support using Jetpack Compose, Room database, and Paging 3.

## 🏗️ Architecture

The app follows **Clean Architecture** principles with three main layers:

### Presentation Layer (`presentation/`)

- **MainActivity**: Main activity with search interface
- **MainViewModel**: Manages data flows and search state
- **ListDeliverers**: Displays all cached deliverers
- **SearchDeliverer**: Shows remote search results
- **UI Theme**: Material Design 3 theming

### Domain Layer (`domain/`)

- **Model**: `Deliverer` - Core business entity
- **Repository**: `DelivererRepository` - Data access interface
- **Use Cases**:
    - `GetAllDeliverersUseCase` - Retrieves all cached deliverers
    - `GetDeliverersFromRemoteMediator` - Remote search with caching
    - `GetDeliverersFromLocalSearch` - Local database search

### Data Layer (`data/`)

- **Repository**: `DelivererRepositoryImpl` - Repository implementation
- **Database**: Room database with `DelivererEntity` and `RemoteKey`
- **Network**: Retrofit API service with DTOs
- **Mappers**: Data transformation between layers
- **RemoteMediator**: Handles online/offline data synchronization

## 🔧 Key Features

### 1. **Three Search Modes**

- **All Deliverers**: Shows cached data when no search query
- **Remote Search**: Searches API and caches results locally
- **Local Search**: Pattern-matching search in cached data only

### 2. **Offline Support**

- RemoteMediator pattern for seamless online/offline experience
- Local database caching with Room
- Pagination state management

### 3. **Modern UI**

- Jetpack Compose with Material Design 3
- Lazy loading with pagination
- Loading and error states
- Debounced search (1-second delay)

### 4. **Clean Code**

- Dependency injection with Hilt
- MVVM pattern with reactive streams
- Comprehensive documentation
- Removed unused code and dependencies

## 📱 How It Works

### User Experience

1. **Empty Search**: App displays all cached deliverers
2. **Type Query**: After 1-second debounce, searches remotely
3. **Offline Mode**: Falls back to local search automatically
4. **Pagination**: Loads more data as user scrolls

### Data Flow

```
UI → ViewModel → UseCase → Repository → [API/Database] → Mappers → Domain Models
```

### Search Types

- **Remote**: `deliverers` flow - searches API, caches locally
- **Local**: `localDeliverers` flow - searches cached data only
- **All**: `deliverersPaged` flow - shows all cached deliverers

## 🛠️ Technical Stack

- **UI**: Jetpack Compose + Material Design 3
- **Architecture**: MVVM + Clean Architecture
- **DI**: Dagger Hilt
- **Database**: Room with pagination
- **Network**: Retrofit + Gson
- **Async**: Kotlin Coroutines + Flow
- **Pagination**: Paging 3 with RemoteMediator

## 📄 Project Structure

```
app/src/main/java/gaur/himanshu/imagesearchapp/
├── presentation/           # UI layer
│   ├── MainActivity.kt     # Main screen
│   ├── MainViewModel.kt    # State management
│   ├── ListDeliverers.kt   # All deliverers view
│   ├── SearchDeliverer.kt  # Search results view
│   └── ui/theme/          # Material theming
├── domain/                # Business logic
│   ├── model/             # Domain entities
│   ├── repository/        # Repository interface
│   └── useCase/           # Business use cases
├── data/                  # Data access
│   ├── repository/        # Repository implementation
│   ├── model/            # Data models
│   │   ├── local/        # Database entities & DAOs
│   │   └── remote/       # API DTOs & service
│   ├── mappers/          # Data transformations
│   ├── pagingSource/     # RemoteMediator
│   └── di/               # Dependency injection
├── AppDatabase.kt        # Room database setup
└── BaseApplication.kt    # Application class
```

## 🔍 Database Schema

### DelivererEntity

- `id`: Unique identifier from API
- `name`: Deliverer display name
- `query`: Search query that fetched this record

### RemoteKey

- `id`: Entity identifier
- `prevKey`: Previous page key
- `nextKey`: Next page key
- `query`: Associated search query

## 🚀 Getting Started

1. Clone the repository
2. Open in Android Studio
3. Build and run on device/emulator
4. Start searching for deliverers!

## 💡 Key Improvements Made

### Code Cleanup

- ✅ Removed unused `GetDeliverersUseCase`
- ✅ Removed unused `DeliverersList` composable
- ✅ Removed unused `DelivererPagingSource`
- ✅ Removed unused `DelivererDotToDelivererMapper`
- ✅ Removed boilerplate test files
- ✅ Cleaned up unused imports and methods

### Documentation

- ✅ Added comprehensive comments to all classes
- ✅ Documented method parameters and return types
- ✅ Explained architecture decisions
- ✅ Added usage examples in comments

### Code Quality

- ✅ Consistent formatting and naming
- ✅ Proper separation of concerns
- ✅ Single responsibility principle
- ✅ Clean architecture implementation

This app demonstrates modern Android development best practices with a clean, maintainable codebase
ready for production use.