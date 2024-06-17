plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android-extensions")
}

android {
    namespace = "com.example.aws_application"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.aws_application"
        minSdk = 26
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
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = false
    }
}

dependencies {

    implementation ("androidx.core:core-ktx:1.13.1")
    implementation ("androidx.appcompat:appcompat:1.2.0")
    implementation ("com.google.android.material:material:1.2.1")
    implementation ("androidx.constraintlayout:constraintlayout:2.0.2")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.3.1")
    implementation ("androidx.navigation:navigation-ui-ktx:2.3.1")
    implementation ("androidx.compose.material3:material3:1.2.0-alpha04")
    implementation ("com.google.android.material:material:1.11.0-alpha01")
    testImplementation ("junit:junit:4.+")
    androidTestImplementation ("androidx.test.ext:junit:1.1.2")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.3.0")
    implementation ("com.amplifyframework:aws-api:1.28.0")
    implementation ("com.amplifyframework:aws-auth-cognito:1.4.0")
    implementation ("com.amplifyframework:aws-storage-s3:1.28.0")
    implementation ("com.amplifyframework:aws-datastore:1.28.0")
    implementation ("com.amplifyframework:core:1.28.0")

    coreLibraryDesugaring ("com.android.tools:desugar_jdk_libs:2.0.4")

}
