pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "NossoTCC"
include(":app")

plugins {
    kotlin("android") version "1.9.24" apply false

    // AGP atualizado para resolver o erro do core-ktx 1.17.0
    id("com.android.application") version "8.9.1" apply false
    id("com.android.library") version "8.9.1" apply false

    id("com.google.gms.google-services") version "4.4.4" apply false
}
