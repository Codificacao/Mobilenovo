plugins {
    // Plugin Kotlin compatível
    kotlin("android") version "1.9.24" apply false

    // Android Gradle Plugin atualizado (resolve o erro das dependências)
    id("com.android.application") version "8.9.1" apply false
    id("com.android.library") version "8.9.1" apply false

    id("com.google.gms.google-services") version "4.4.4" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
