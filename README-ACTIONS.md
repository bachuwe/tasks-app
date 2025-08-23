# GitHub Actions CI/CD Documentation

This repository includes several GitHub Actions workflows for continuous integration and deployment.

## Available Workflows

### 1. CI Workflow (`ci.yml`)
**Purpose**: Complete CI pipeline with separate jobs for different phases  
**Triggers**: Push to `main`/`develop`, Pull requests to `main`/`develop`

**Jobs:**
- **Unit Tests**: Runs `./gradlew test` with test report uploads
- **Build**: Creates debug APK after tests pass (`./gradlew assembleDebug`)
- **Instrumented Tests**: Runs UI tests on Android emulator (`./gradlew connectedAndroidTest`)

**Artifacts:**
- Test reports
- Debug APK
- Instrumented test reports

### 2. Build and Test Workflow (`build-and-test.yml`)
**Purpose**: Simple workflow combining build and test in a single job  
**Triggers**: Push to `main`/`develop`, Pull requests to `main`/`develop`

**Steps:**
1. Run unit tests (`./gradlew test`)
2. Build debug APK (`./gradlew assembleDebug`)
3. Build release APK (`./gradlew assembleRelease`)

**Artifacts:**
- Test reports
- Debug and Release APKs

### 3. Tests Workflow (`tests.yml`)
**Purpose**: Focused testing with enhanced reporting  
**Triggers**: Push to `main`/`develop`, Pull requests, Manual dispatch

**Features:**
- Unit test execution
- Test result reporting in PR comments
- Detailed test report uploads

## Environment Setup

All workflows use:
- **Java**: OpenJDK 17 (Temurin distribution)
- **Gradle**: Version defined in `gradle-wrapper.properties`
- **Android SDK**: Automatically installed by GitHub Actions
- **Caching**: Gradle dependencies cached for faster builds

## Gradle Commands Used

### Unit Testing
```bash
./gradlew test --stacktrace
./gradlew testDebugUnitTest --stacktrace
```

### Building
```bash
./gradlew assembleDebug --stacktrace
./gradlew assembleRelease --stacktrace
```

### Instrumented Testing
```bash
./gradlew connectedAndroidTest --stacktrace
```

## Emulator Configuration (for Instrumented Tests)

- **API Level**: 29 (Android 10)
- **Target**: Google APIs
- **Architecture**: x86_64
- **Profile**: Nexus 6
- **Features**: Hardware acceleration, no audio, no animations

## Artifacts and Reports

### Test Reports
- Location: `app/build/reports/tests/`
- Format: HTML reports with detailed test results
- Available for download from workflow runs

### APK Files
- Debug APK: `app/build/outputs/apk/debug/`
- Release APK: `app/build/outputs/apk/release/`
- Automatically uploaded as workflow artifacts

## Usage Examples

### Running Locally
These commands mirror what the GitHub Actions run:

```bash
# Unit tests (as documented in README.md)
./gradlew test

# Build APK
./gradlew assembleDebug

# Instrumented tests (requires connected device/emulator)
./gradlew connectedAndroidTest
```

### Triggering Workflows

1. **Automatic**: Push code or create PR to `main` or `develop`
2. **Manual**: Go to Actions tab → Select "Tests" workflow → "Run workflow"

## Troubleshooting

### Common Issues
1. **Build failures**: Check Java version compatibility
2. **Test failures**: Review test reports in artifacts
3. **Emulator issues**: Android emulator may be slow in CI environment

### Debug Information
All workflows run with `--stacktrace` for detailed error information.

## Customization

To modify the workflows:
1. Edit `.github/workflows/*.yml` files
2. Adjust Java version, Android API level, or other parameters as needed
3. Add/remove steps based on project requirements

## Related Documentation
- Main project setup: `README.md`
- Android Gradle Plugin: [Official documentation](https://developer.android.com/studio/build)
- GitHub Actions: [Official documentation](https://docs.github.com/en/actions)