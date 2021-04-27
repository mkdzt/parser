import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.32"
    application
}

group = "me.maksim"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies{
    implementation("org.litote.kmongo:kmongo-coroutine:4.2.6")
    implementation("org.litote.kmongo:kmongo:4.2.6")
    implementation ("org.jsoup:jsoup:1.13.1")
}


tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "MainKt"
}