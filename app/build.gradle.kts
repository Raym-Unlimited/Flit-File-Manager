plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.raym.flitfilemanager"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.raym.flitfilemanager"
        minSdk = 27
        targetSdk = 33
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.1")
    implementation("com.google.firebase:firebase-ads:22.3.0")
    implementation("androidx.viewpager:viewpager:1.1.0-alpha01")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Added dependencies
    implementation(files(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs"))))
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.preference:preference:1.2.1")
    implementation("androidx.recyclerview:recyclerview:1.3.1")
    implementation("com.google.android.material:material:1.11.0-alpha02")
    implementation("com.genonbeta.android:framework:1.0.2.8")
    implementation("com.genonbeta.android:gdatabase:1.0.2.6.1")
    implementation("com.genonbeta.coolsocket:main:1.0.3.1")
    implementation("com.google.zxing:core:3.5.2")
    implementation("org.nanohttpd:nanohttpd:2.3.1")
    implementation("com.velitasali.tools.android:zxing:3.6.3")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("androidx.annotation:annotation:1.7.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")
    implementation("com.github.bumptech.glide:okhttp3-integration:4.16.0")
    implementation("com.afollestad.material-dialogs:core:3.3.0")
    implementation("com.github.jaiselrahman:FilePicker:1.3.2")
    implementation("com.karumi:dexter:6.2.3")
}