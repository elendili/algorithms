/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    java
    `maven-publish`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    implementation("com.google.code.gson:gson:2.8.7")
    implementation("org.junit.jupiter:junit-jupiter-engine:5.11.2")
    implementation("org.junit.jupiter:junit-jupiter-params:5.11.2")
    implementation("org.hamcrest:hamcrest:2.2")
    implementation("org.apache.logging.log4j:log4j-api:2.5")
    implementation("org.apache.logging.log4j:log4j-core:2.13.2")
    implementation("org.openjdk.jmh:jmh-core:1.19")
    implementation("org.openjdk.jmh:jmh-generator-annprocess:1.19")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.4.0")
    implementation("org.awaitility:awaitility:4.2.0")
}

group = "sm"
version = "1.0-SNAPSHOT"
description = "algorithms"
java.sourceCompatibility = JavaVersion.VERSION_17

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}
