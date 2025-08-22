# Tasks App

A modern Android application for managing personal tasks and to-do lists. Built with Kotlin using MVVM architecture and Room database for local persistence.

## Features

- ✅ **Add Tasks** - Create new tasks with title and description
- ✏️ **Edit Tasks** - Modify existing tasks
- ✅ **Mark Complete/Incomplete** - Toggle task completion status
- 🗑️ **Delete Tasks** - Remove tasks with confirmation dialog
- 📱 **Material Design UI** - Modern, intuitive user interface
- 💾 **Local Storage** - All data stored locally using Room database
- 🔄 **Real-time Updates** - Live data updates using LiveData
- 📊 **Filter Views** - Switch between active and completed tasks

## Screenshots

*Screenshots will be available once the app is built and tested*

## Architecture

This app follows the **MVVM (Model-View-ViewModel)** architecture pattern:

- **Model**: Room database with Task entity, DAO, and Repository
- **View**: Activities and RecyclerView adapters
- **ViewModel**: Manages UI-related data and business logic

### Key Components

- **Room Database**: Local SQLite database for task persistence
- **LiveData**: Reactive data observation for UI updates
- **ViewBinding**: Type-safe view binding
- **Material Design Components**: Modern UI components
- **Coroutines**: Asynchronous programming for database operations

## Project Structure

```
app/
├── src/main/java/com/bachuwe/tasksapp/
│   ├── data/
│   │   ├── Task.kt                 # Task entity
│   │   ├── TaskDao.kt              # Database access object
│   │   ├── TaskDatabase.kt         # Room database
│   │   └── DateConverters.kt       # Type converters for Room
│   ├── repository/
│   │   └── TaskRepository.kt       # Data repository
│   ├── ui/
│   │   ├── adapter/
│   │   │   └── TaskAdapter.kt      # RecyclerView adapter
│   │   ├── viewmodel/
│   │   │   └── TaskViewModel.kt    # ViewModel for tasks
│   │   └── AddEditTaskActivity.kt  # Add/Edit task screen
│   └── MainActivity.kt             # Main screen
├── src/main/res/
│   ├── layout/                     # XML layouts
│   ├── values/                     # Colors, strings, themes
│   ├── drawable/                   # Vector icons
│   └── menu/                       # Menu resources
└── src/test/                       # Unit tests
```

## Setup Instructions

### Prerequisites

- **Android Studio**: Arctic Fox (2020.3.1) or later
- **Android SDK**: API level 21 (Android 5.0) or higher
- **Kotlin**: 1.9.10 or later
- **Gradle**: 8.0 or later

### Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/bachuwe/tasks-app.git
   cd tasks-app
   ```

2. **Open in Android Studio**:
   - Launch Android Studio
   - Choose "Open an Existing Project"
   - Navigate to the cloned repository folder
   - Click "OK"

3. **Build the project**:
   ```bash
   ./gradlew build
   ```

4. **Run the app**:
   - Connect an Android device or start an emulator
   - Click the "Run" button in Android Studio
   - Or use command line: `./gradlew installDebug`

### Running Tests

**Unit Tests**:
```bash
./gradlew test
```

**Instrumented Tests** (requires connected device/emulator):
```bash
./gradlew connectedAndroidTest
```

## Dependencies

### Core Dependencies
- `androidx.core:core-ktx` - Kotlin extensions
- `androidx.appcompat:appcompat` - Backwards compatibility
- `com.google.android.material:material` - Material Design components
- `androidx.constraintlayout:constraintlayout` - Layout manager

### Architecture Components
- `androidx.lifecycle:lifecycle-viewmodel-ktx` - ViewModel
- `androidx.lifecycle:lifecycle-livedata-ktx` - LiveData
- `androidx.room:room-runtime` - Room database
- `androidx.room:room-ktx` - Room Kotlin extensions

### UI Components
- `androidx.recyclerview:recyclerview` - List display
- `androidx.fragment:fragment-ktx` - Fragment utilities
- `androidx.activity:activity-ktx` - Activity utilities

### Testing
- `junit:junit` - Unit testing framework
- `androidx.test.ext:junit` - Android testing extensions
- `androidx.test.espresso:espresso-core` - UI testing

## Usage

### Adding a Task
1. Tap the floating action button (+) on the main screen
2. Enter a task title (required)
3. Optionally add a description
4. Tap "Save"

### Editing a Task
1. Tap on any task in the list
2. Modify the title or description
3. Tap "Save"

### Completing a Task
1. Tap the checkbox next to any task
2. The task will be marked as complete and become semi-transparent

### Deleting a Task
1. Tap the three-dot menu next to any task
2. Select "Delete"
3. Confirm deletion in the dialog

### Viewing Completed Tasks
1. Tap the menu button (⋮) in the top toolbar
2. Select "Show Completed"
3. Switch back by selecting "Show Active"

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Author

**Ayikem Cosmas Awupuri** - [bachuwe](https://github.com/bachuwe)

## Acknowledgments

- Material Design guidelines for UI/UX inspiration
- Android Architecture Components documentation
- Room database documentation and examples

---

For questions or support, please open an issue on GitHub.