# Installing Tasks App on Your Android Device

This guide will help you install the Tasks App on your Android device. There are several ways to get the app installed.

## Quick Installation (Recommended)

### Option 1: Download from Releases (Stable)
1. **Go to Releases**: Visit [GitHub Releases](https://github.com/bachuwe/tasks-app/releases)
2. **Download APK**: Download the latest `app-release.apk` file (recommended, ~5MB)
3. **Enable Unknown Sources**: 
   - On your Android device, go to Settings → Security → Unknown Sources
   - Or Settings → Apps → Install unknown apps → (your browser) → Allow from this source
4. **Install**: 
   - Open the downloaded APK file
   - Tap "Install" when prompted
   - Wait for installation to complete
5. **Open the app**: Look for "Tasks App" in your app drawer

### Option 2: Download from GitHub Actions (Latest Build)
1. **Go to Actions**: Visit [GitHub Actions](https://github.com/bachuwe/tasks-app/actions)
2. **Find Latest Build**: Click on the latest successful CI workflow run
3. **Download Artifact**: Scroll down to "Artifacts" and download "debug-apk"
4. **Extract and Install**: Extract the zip file and install the APK as described above

## Installation Options Explained

| APK Type | File Size | When to Use | Signed |
|----------|-----------|-------------|--------|
| **app-release.apk** | ~5MB | Regular use, recommended | ✅ Yes |
| **app-debug.apk** | ~6MB | Development, testing | ✅ Yes |

Both APKs are fully functional and signed for installation.

## System Requirements

- **Android Version**: 5.0 (API level 21) or higher
- **Storage Space**: ~10MB free space
- **Permissions**: Storage access (for the app database)

## Troubleshooting

### "App not installed" Error
- Make sure you have enough storage space
- Enable "Install from unknown sources" in your device settings
- Try restarting your device and installing again

### "Parse Error"
- The APK file may be corrupted. Try downloading it again
- Make sure you're using a compatible Android version (5.0+)

### Security Warning
- Android will show a security warning because the app is not from Google Play Store
- This is normal - tap "Install anyway" or "Install"
- The app is safe and doesn't require any dangerous permissions

## Alternative: Build from Source

If you prefer to build the app yourself:

1. **Clone Repository**:
   ```bash
   git clone https://github.com/bachuwe/tasks-app.git
   cd tasks-app
   ```

2. **Build APK**:
   ```bash
   ./gradlew assembleRelease
   ```

3. **Find APK**: The built APK will be in `app/build/outputs/apk/release/`

## App Features

Once installed, the Tasks App provides:

- ✅ **Add Tasks** - Create new tasks with title and description
- ✏️ **Edit Tasks** - Modify existing tasks  
- ✅ **Mark Complete/Incomplete** - Toggle task completion status
- 🗑️ **Delete Tasks** - Remove tasks with confirmation dialog
- 📱 **Material Design UI** - Modern, intuitive user interface
- 💾 **Local Storage** - All data stored locally using Room database
- 🔄 **Real-time Updates** - Live data updates using LiveData
- 📊 **Filter Views** - Switch between active and completed tasks

## Support

If you encounter any issues:
1. Check this troubleshooting guide
2. Open an issue on [GitHub Issues](https://github.com/bachuwe/tasks-app/issues)
3. Include your Android version and device model in the issue description

---

**Note**: This app stores all data locally on your device. Your tasks are private and not sent to any external servers.