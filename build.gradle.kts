import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.2.21"
    kotlin("plugin.serialization") version "2.2.0"
    kotlin("plugin.allopen") version "2.2.21"
    id("app.cash.sqldelight") version "2.1.0"
    id("maven-publish")
}

group = "org.labcluster.crm"
version = "1.0.23-SNAPSHOT"

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.7.1")

    //SQLDelight
    implementation("app.cash.sqldelight:coroutines-extensions:2.1.0")
    implementation("app.cash.sqldelight:sqlite-driver:2.0.2")

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

sqldelight {
    databases {
        create("Database") {
            packageName.set("org.labcluster.crm.shared")
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenLocal") {
            from(components["java"])
        }
    }
}