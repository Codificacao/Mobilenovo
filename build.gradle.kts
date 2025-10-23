plugins {
    // Plugin Kotlin com versão definida
    kotlin("android") version "1.9.10" apply false
    id("com.android.application") version "8.8.0" apply false
    id("com.android.library") version "8.8.0" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
