plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'com.google.firebase.crashlytics'
    id 'com.google.firebase.firebase-perf'
    id 'kotlin-kapt'
    id 'dagger.hilt.android.plugin'
    id 'org.jmailen.kotlinter'
    id 'com.google.gms.google-services'
}

android {
    compileSdk 33

    defaultConfig {
        applicationId "com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver"
        minSdk 28
        targetSdk 33
        versionCode 3
        versionName "0.0.15"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary true
        }
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_11
        targetCompatibility JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        compose true
    }
    composeOptions {
        kotlinCompilerExtensionVersion "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += '/META-INF/{AL2.0,LGPL2.1}'
        }
    }
    namespace 'com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver'
}

dependencies {

    implementation 'androidx.core:core-ktx:1.9.0'

    implementation "androidx.compose.ui:ui:$compose_version"

    implementation "androidx.compose.ui:ui-tooling-preview:$compose_version"
    implementation "androidx.compose.material:material:$compose_version"
    implementation "androidx.compose.material:material-icons-extended:$compose_version"
    implementation "androidx.compose.material:material-icons-core:$compose_version"
    implementation "androidx.compose.material3:material3:1.0.1"

    implementation "androidx.core:core-splashscreen:1.0.0"
    implementation "com.github.cerve-development:CerveUiLibrary:0.1.1"

    implementation "com.google.accompanist:accompanist-systemuicontroller:0.28.0"
    implementation "com.google.accompanist:accompanist-flowlayout:0.27.0"
    implementation "com.google.accompanist:accompanist-permissions:0.27.1"

    implementation "androidx.navigation:navigation-compose:2.5.3"

    implementation "androidx.hilt:hilt-navigation-compose:1.0.0"
    implementation "com.google.dagger:hilt-android:2.44.2"

    kapt "com.google.dagger:hilt-compiler:2.44.2"

    implementation "androidx.datastore:datastore-preferences:1.0.0"

    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.5.1'
    implementation 'androidx.activity:activity-compose:1.6.1'

    implementation "androidx.camera:camera-camera2:1.1.0"
    implementation "androidx.camera:camera-core:1.1.0"
    implementation "androidx.camera:camera-lifecycle:1.1.0"
    implementation "androidx.camera:camera-extensions:1.1.0"
    implementation "androidx.camera:camera-view:1.1.0"

    implementation "com.google.mlkit:text-recognition:16.0.0-beta6"
    implementation "com.google.mlkit:digital-ink-recognition:18.0.0"

    implementation "org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5"

    implementation 'com.google.firebase:firebase-analytics-ktx:21.2.0'
    implementation "com.google.firebase:firebase-perf-ktx:20.3.0"
    implementation "com.google.firebase:firebase-crashlytics-ktx:18.3.2"
    implementation "com.jakewharton.timber:timber:5.0.1"

    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.4'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.0'
    androidTestImplementation "androidx.compose.ui:ui-test-junit4:$compose_version"
    debugImplementation "androidx.compose.ui:ui-tooling:$compose_version"
    debugImplementation "androidx.compose.ui:ui-test-manifest:$compose_version"
}