import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver"
        minSdk = 28
        targetSdk = 35
        versionCode = 3
        versionName = "0.1.$versionCode"
    }

    buildTypes {
        release {
            // Enables code shrinking, obfuscation, and optimization for only
            // your project's release build type. Make sure to use a build
            // variant with `isDebuggable=false`.
            isMinifyEnabled = true

            // Enables resource shrinking, which is performed by the
            // Android Gradle plugin.
            isShrinkResources = true

            proguardFiles(
                // Includes the default ProGuard rules files that are packaged with
                // the Android Gradle plugin. To learn more, go to the section about
                // R8 configuration files.
                getDefaultProguardFile("proguard-android-optimize.txt"),

                // Includes a local, custom Proguard rules file
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = JvmTarget.JVM_21.target
    }
}

dependencies {

    implementation(libs.android.compose.ui)
    implementation(libs.android.compose.ui.tooling.preview)
    implementation(libs.android.compose.material3)
    implementation(libs.android.compose.activity)

    implementation(libs.play.app.update)

    debugImplementation(libs.android.compose.ui.tooling)
}