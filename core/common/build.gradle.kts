plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.picpay.desafio.common"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    api(libs.androidx.core.ktx)
    api(libs.androidx.navigation.compose)
    api(libs.koin.androidx.compose)
    api(libs.androidx.lifecycle.runtime.ktx)
    api(platform(libs.koin.bom))
    api(libs.koin.androidx.compose)
    api(libs.koin.core)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}