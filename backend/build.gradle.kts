import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

group = "dat257.gyro"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(project(":shared"))
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("org.jetbrains.exposed:exposed-core:0.34.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.34.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.34.1")
    implementation("org.jetbrains.exposed:exposed-java-time:0.34.1")
    implementation("org.postgresql:postgresql:42.2.2")
    implementation("com.h2database:h2:1.4.199")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}