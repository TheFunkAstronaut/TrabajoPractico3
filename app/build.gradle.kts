plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.trabajopractico3"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.trabajopractico3"
        minSdk = 29
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    val nav_version = "2.8.2"
    // Jetpack Compose Integration
    implementation (libs.androidx.navigation.compose)
    // Views/Fragments Integration
    implementation (libs.androidx.navigation.fragment)
    implementation (libs.androidx.navigation.ui)
    // Feature module support for Fragments
    implementation (libs.androidx.navigation.dynamic.features.fragment)
    // Pa el viewpager
    implementation (libs.androidx.viewpager2)
    //Pa el Glide
    implementation (libs.glide)
    annotationProcessor (libs.compiler)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}