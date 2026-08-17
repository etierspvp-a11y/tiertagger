# Quick Build Guide for Windows

If you're having issues with Gradle wrapper, use this alternative method:

## Method 1: Using IntelliJ IDEA (EASIEST)

1. Download **IntelliJ IDEA Community Edition** (free): https://www.jetbrains.com/idea/download/
2. Open the tiertagger folder in IntelliJ
3. IntelliJ will auto-detect it's a Gradle project
4. Right-click `build.gradle` → **Run Gradle** → **build**
5. The mod will be in `build/libs/tiertagger-1.0.0.jar`

## Method 2: Using Command Line (If Gradle is installed)

Make sure you have Gradle installed globally:
```powershell
gradle --version
```

If installed, in the project folder run:
```powershell
gradle build
```

## Method 3: Manual Build (No Gradle needed)

The pre-compiled mod is available here:
https://github.com/etierspvp-a11y/tiertagger/releases

Or ask for the JAR file directly.

## If all else fails:

1. Make sure Java is installed:
```powershell
java -version
```

2. If Java is not found, install it from: https://www.oracle.com/java/technologies/downloads/

3. Try deleting the `.gradle` folder and rebuilding
