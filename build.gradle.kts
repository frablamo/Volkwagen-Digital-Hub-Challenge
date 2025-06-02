plugins {
    kotlin("jvm") version "2.1.20"
}

group = "code.challenge"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk-jvm:1.13.12")
    testImplementation("org.jetbrains.kotlin:kotlin-test:2.1.20")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:2.1.20")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.20")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.6.2")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "volkwagen.cleaningrobots.MainKt"
    }
    // Optionally, include all dependencies in the jar (fat jar)
    from({
        configurations.runtimeClasspath
            .get()
            .filter { it.name.endsWith("jar") }
            .map { zipTree(it) }
    })
}
