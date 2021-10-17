

group = "dat257.gyro"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

buildscript {

    val kotlinVersion = "1.5.31"
    val daggerHiltVersion = "2.28-alpha"
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    dependencies {
        classpath( "com.android.tools.build:gradle:7.0.3")
        classpath( "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$daggerHiltVersion")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
