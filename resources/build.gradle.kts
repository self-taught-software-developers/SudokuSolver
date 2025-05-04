import org.apache.tools.ant.util.ScriptManager.auto

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.multiplatform.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose)
}
kotlin {

// Target declarations - add or remove as needed below. These define
// which platforms this KMP module supports.
// See: https://kotlinlang.org/docs/multiplatform-discover-project.html#targets
    androidLibrary {
        namespace = "com.stsd.selftaughtsoftwaredevelopers.resources"
        compileSdk = 35
        minSdk = 29

    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "resources"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(compose.components.resources)
            implementation(compose.runtime)
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.stsd.selftaughtsoftwaredevelopers.resources"
    generateResClass = auto
}