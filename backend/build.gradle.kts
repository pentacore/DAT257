import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

group = "dat257.gyro"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(project(":shared"))
    implementation("ch.qos.logback:logback-classic:1.2.6")
    implementation("org.jetbrains.exposed:exposed-core:0.35.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.34.2")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.34.2")
    implementation("org.jetbrains.exposed:exposed-java-time:0.34.2")
    implementation("org.postgresql:postgresql:42.2.23.jre7")
    implementation("com.h2database:h2:1.4.200")
    implementation(platform("org.http4k:http4k-bom:4.13.1.0"))
    implementation("org.http4k:http4k-core:4.13.1.0")
    implementation("org.http4k:http4k-client-apache:4.13.1.0")
    implementation("org.http4k:http4k-server-netty:4.13.1.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}