import java.util.Properties // <--- 1. A SOLUÇÃO DO ERRO ESTÁ AQUI

plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.nossotcc"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.nossotcc"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        // --- LENDO A CHAVE ---
        val properties = Properties() // Agora funciona porque importamos lá em cima
        val localPropertiesFile = project.rootProject.file("local.properties")
        if (localPropertiesFile.exists()) {
            localPropertiesFile.inputStream().use { inputStream ->
                properties.load(inputStream)
            }
        }

        val apiKey = properties.getProperty("apiKey") ?: ""
        buildConfigField("String", "API_KEY", "\"$apiKey\"")
        // ---------------------
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
        compose = true // Necessário para o Chatbot
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // Rede e JSON
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Android Básico
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.recyclerview:recyclerview:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // Gráficos
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:34.4.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")

    // --- IA (GEMINI) ---
    implementation("com.google.ai.client.generativeai:generativeai:0.9.0")

    // --- INTERFACE DO CHATBOT (COMPOSE) ---
    // O BOM garante que todas as versões do Compose sejam compatíveis
    val composeBom = platform("androidx.compose:compose-bom:2024.04.01")
    implementation(composeBom)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3") // Design moderno
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Libs do catálogo (libs.versions.toml)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.core.ktx)
}