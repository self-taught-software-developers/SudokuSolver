import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask
import kotlin.jvm.java

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.multiplatform.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {

    applyDefaultHierarchyTemplate()
    jvmToolchain(21)

// Target declarations - add or remove as needed below. These define
// which platforms this KMP module supports.
// See: https://kotlinlang.org/docs/multiplatform-discover-project.html#targets
    androidLibrary {
        namespace = "com.stsd.selftaughtsoftwaredevelopers.shared"
        compileSdk = 35
        minSdk = 29
    }

// For iOS targets, this is also where you should
// configure native binary output. For more information, see:
// https://kotlinlang.org/docs/multiplatform-build-native-binaries.html#build-xcframeworks
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

// Source set declarations.
// Declaring a target automatically creates a source set with the same name. By default, the
// Kotlin Gradle Plugin creates additional source sets that depend on each other, since it is
// common to share sources between related targets.
// See: https://kotlinlang.org/docs/multiplatform-hierarchy.html
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.resources)

                with(compose) {
                    implementation(ui)
                    implementation(runtime)
                    implementation(foundation)

                    implementation(material3)
                    implementation(materialIconsExtended)
                    implementation(components.resources)
                    implementation(components.uiToolingPreview)

                    implementation(uiTooling)
                    implementation(preview)
                }

                with(libs) {

                    api(ui.navigation)
                    implementation(ui.helper)
                    implementation(ui.canvas)
                    implementation(ui.components)

                    implementation(data.result)
                    implementation(serialization)

                    implementation(project.dependencies.platform(libs.koin.annotation.bom))
                    implementation(project.dependencies.platform(libs.koin.bom))

                    implementation(libs.koin.core)
                    implementation(libs.koin.annotation)
                    implementation(libs.koin.compose)
                    implementation(libs.koin.compose.viewmodel)
                    implementation(libs.koin.compose.viewmodel.navigation)

                    implementation(compose.viewmodel)
                    implementation(compose.navigation)
                }
            }
        }
    }

    // KSP Common sourceSet
    sourceSets.named("commonMain").configure {
        kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
    }

    compilerOptions {
        freeCompilerArgs.add("-Xcontext-receivers")
    }

}

dependencies {
    kspCommonMainMetadata(libs.koin.annotation.ksp)
    kspAndroid(libs.koin.annotation.ksp)
}

project.tasks.withType(KotlinCompilationTask::class.java).configureEach {
    if(name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

ksp {
    arg("KOIN_CONFIG_CHECK","true")
    arg("KOIN_DEFAULT_MODULE", "false") // Optional: Disable default module generation
    arg("KOIN_USE_COMPOSE_VIEWMODEL","true")
}