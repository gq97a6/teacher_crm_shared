import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.2.21"
    kotlin("plugin.serialization") version "2.2.0"
    kotlin("plugin.allopen") version "2.2.21"
    id("maven-publish")
}

group = "org.labcluster.crm"
version = "2.0.0"

allOpen {
    annotation("org.labcluster.crm.shared.Open")
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.7.1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)

    compilerOptions {
        jvmTarget = JvmTarget.fromTarget("21")
        freeCompilerArgs.add("-opt-in=kotlin.uuid.ExperimentalUuidApi")
        freeCompilerArgs.add("-opt-in=kotlin.time.ExperimentalTime")
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenLocal") {
            from(components["java"])
        }
    }
}