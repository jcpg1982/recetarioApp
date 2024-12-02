plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "pe.com.master.machines.constants"
    compileSdk = DefaultConfigVersions.compileSdk

    defaultConfig {
        minSdk = DefaultConfigVersions.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        buildConfigField("String", "KEYSTORE_ALIAS", "\"ÑoñoCh4voK1k0DoñaFl0r1nd4J4kM0t3r0\"")
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
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.adapter.rxjava2)
    implementation(libs.converter.moshi)
    implementation(libs.converter.gson)
    implementation(libs.moshi.kotlin)
    implementation(libs.library.no.op)
}