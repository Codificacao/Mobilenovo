plugins {
    // Plugin Kotlin compatível
    kotlin("android") version "1.9.10" apply false

    // Versão do Android Gradle Plugin compatível com o seu Android Studio
    id("com.android.application") version "8.8.0" apply false
    id("com.android.library") version "8.8.0" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
