# Starter code for making Android Application using Kotlin + Jetpack Compose

## How to use
1. Clone the repository
2. Open the project in Android Studio
3. Modify these:
   - `rootProject.name` in `settings.gradle.kts`
   - `android.namespace` in `app/build.gradle.kts`
   - `applicationId` in `app/build.gradle.kts`
   - `app_name` in `res/values/strings.xml`
   - Root folder name
   - com.ahargunyllib.hackathon_kotlin_starter package name
4. Start building your app!

## Tech Stack
- [Kotlin](https://kotlinlang.org/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
- [Navigation Compose](https://developer.android.com/jetpack/compose/navigation)
- [Coil](https://coil-kt.github.io/coil/)
- [Retrofit](https://square.github.io/retrofit/)
- [Room](https://developer.android.com/jetpack/androidx/releases/room)

## Directory Structure

```markdown
├── app
│   ├── src
│   │   ├── main
│   │   │   ├── java/com/ahargunyllib/hackathon_kotlin_starter
│   │   │   │   ├── data
│   │   │   │   │   ├── local
│   │   │   │   │   ├── remote
│   │   │   │   │   │   └── response
│   │   │   │   │   └── repository
│   │   │   │   ├── di
│   │   │   │   ├── domain
│   │   │   │   │   ├── model
│   │   │   │   │   ├── repository
│   │   │   │   │   └── service
│   │   │   │   ├── presentation
│   │   │   │   │   ├── adapter
│   │   │   │   │   ├── view
│   │   │   │   │   ├── viewmodel
│   │   │   │   │   └── ui
│   │   │   │   │       ├── design_system
│   │   │   │   │       ├── widget
│   │   │   │   │       └── navigation
│   │   │   │   │           ├── nav_graph
│   │   │   │   │           └── nav_host
│   │   │   │   ├── utils
│   │   │   │   ├── BaseActivity.kt
│   │   │   │   └── BaseApplication.kt
│   │   └── test
│   │       └── java/com/ahargunyllib/hackathon_kotlin_starter
│   │           ├── YourTest.kt
```
