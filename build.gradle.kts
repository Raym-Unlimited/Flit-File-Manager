// Top-level build file where you can add configuration options common to all sub-projects/modules.
// Top-level build file where you can add configuration options common to all sub-projects/modules.
//buildscript {
//    repositories {
//        google()
//        mavenCentral()
//    }
//    dependencies {
//        classpath("com.android.tools.build:gradle:4.2.2")
//
//        // NOTE: Do not place your application dependencies here; they belong
//        // in the individual module build.gradle.kts files
//    }
//}

//allprojects {
//    repositories {
//        google()
//        mavenCentral()
////        mavenCentral() // Warning: this repository is going to shut down soon
//        maven(url = "https://jitpack.io")
//    }
//}

//tasks.register("clean", Delete::class) {
//    delete(rootProject.buildDir)
//}

plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
}