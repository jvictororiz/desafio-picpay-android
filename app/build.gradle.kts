plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.picpay.desafio.android"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.picpay.desafio.android"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BASE_URL", "\"https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/\"")
        }

        debug {
            buildConfigField("String", "BASE_URL", "\"https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        buildConfig = true
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(project(":feature:contacts"))
    implementation(project(":core:navigation"))
    implementation(project(":core:api"))
    implementation(project(":core:persistence"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity.compose)
    api(platform(libs.koin.bom))
    api(libs.koin.androidx.compose)
    implementation(libs.androidx.ui.test.junit4.android)
    api(libs.koin.core)
}