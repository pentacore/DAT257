plugins {
    id ("com.android.application")
    id ("kotlin-android")
    id ("kotlin-android-extensions")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version "1.5.31"
}

apply(plugin= "kotlin-kapt")
android {
    compileSdk = 31

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    defaultConfig {
        applicationId = "com.example.experimentalwalkingapp"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility(1.8)
        targetCompatibility(1.8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding=true
    }
}

dependencies {
    implementation("androidx.preference:preference-ktx:1.1.1")
    implementation("com.google.android.gms:play-services-location:18.0.0")
    val kotlinVersion = "1.5.31"
    val coroutinesVersion = "1.4.3"
    val retrofitVersion = "2.9.0"
    val lifeCycleVersion = "2.2.0"
    val daggerHiltVersion = "2.39.1"
    val hiltLifeCycleVersion = "1.0.0-alpha03"
    val coilVersion = "1.2.2"
    val activityVersion = "1.1.0"
    val roomVersion = "2.3.0"



    // Room components
    implementation ("androidx.room:room-runtime:$roomVersion")
    annotationProcessor ("androidx.room:room-compiler:$roomVersion")
    androidTestImplementation ("androidx.room:room-testing:$roomVersion")
    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation ("androidx.room:room-guava:$roomVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    implementation ("androidx.core:core-ktx:1.6.0")
    implementation ("androidx.appcompat:appcompat:1.3.1")
    implementation ("com.google.android.material:material:1.4.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.1")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")

    implementation ("androidx.activity:activity-ktx:$activityVersion")
    implementation ("androidx.fragment:fragment-ktx:$activityVersion")

    //ViewBinding
    implementation ("com.android.databinding:viewbinding:7.0.3")

    //Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$coroutinesVersion")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.1")
    implementation ("com.squareup.retrofit2:converter-scalars:$retrofitVersion")

    //material design
    implementation ("com.google.android.material:material:1.4.0")

    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-extensions:$lifeCycleVersion")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:$lifeCycleVersion")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifeCycleVersion")
    implementation( "androidx.lifecycle:lifecycle-livedata-ktx:$lifeCycleVersion")

    //Hilt for di
    implementation ("com.google.dagger:hilt-android:$daggerHiltVersion")
    kapt ("com.google.dagger:hilt-android-compiler:$daggerHiltVersion")
    // Hilt ViewModel extension
    implementation ("androidx.hilt:hilt-lifecycle-viewmodel:$hiltLifeCycleVersion")
    kapt("androidx.hilt:hilt-compiler:$hiltLifeCycleVersion")

    //for image rendering
    implementation ("io.coil-kt:coil:$coilVersion")

    //osmdroid
    implementation("org.osmdroid:osmdroid-android:6.1.11")

    //kotlinx serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")

}

/* old config
apply(plugin = "kotlin-android")
apply(plugin = "kotlin-kapt")
apply(plugin = "com.gradle.build-scan")
plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("plugin.serialization") version "1.5.31"

}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "dat257.gyro"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures() {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility(1.8)
        targetCompatibility(1.8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
dependencies {

    val coroutinesVersion = "1.4.3"
    val retrofitVersion = "2.9.0"
    val lifeCycleVersion = "2.2.0"
    val daggerHiltVersion = "2.39.1"
    val hiltLifeCycleVersion = "1.0.0-alpha03"
    val coilVersion = "1.2.2"
    val activityVersion = "1.1.0"

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.31")
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    implementation("androidx.activity:activity-ktx:$activityVersion")
    implementation("androidx.fragment:fragment-ktx:$activityVersion")

    //ViewBinding
    implementation("com.android.databinding:viewbinding:7.0.3")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$coroutinesVersion")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")
    implementation("com.squareup.retrofit2:converter-scalars:$retrofitVersion")

    //material design
    implementation("com.google.android.material:material:1.4.0")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-extensions:$lifeCycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifeCycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifeCycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifeCycleVersion")

    //Hilt for di
    implementation("com.google.dagger:hilt-android:$daggerHiltVersion")
    implementation("com.google.dagger:hilt-android-compiler:$daggerHiltVersion")
    // Hilt ViewModel extension
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:$hiltLifeCycleVersion")
    implementation("androidx.hilt:hilt-compiler:$hiltLifeCycleVersion")
    //for image rendering
    implementation("io.coil-kt:coil:$coilVersion")
}
*/