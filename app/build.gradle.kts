plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.serialization.json)
}

android {
    namespace = "pe.com.master.machines.recetario"
    compileSdk = DefaultConfigVersions.compileSdk

    defaultConfig {
        applicationId = "pe.com.master.machines.recetario"
        minSdk = DefaultConfigVersions.minSdk
        targetSdk = DefaultConfigVersions.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersionName.sourceCompatibility
        targetCompatibility = JavaVersionName.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = JavaVersionName.jvmTarget
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(projects.core.design)
    implementation(projects.core.constants)

    implementation(projects.feature.splash)
    implementation(projects.feature.home)
    implementation(projects.feature.detailRecipe)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //hilt injeccion
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.compiler)
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.compiler)
    //navigation
    implementation(libs.androidx.navigation.compose)
    //serialization
    implementation(libs.androidx.serialization.json)
}