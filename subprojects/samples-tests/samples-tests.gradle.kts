import build.*

plugins {
    id("kotlin-library")
}

repositories {
    maven(url = "https://repo.gradle.org/gradle/libs-releases")
    maven(url = "https://repo.gradle.org/gradle/ext-releases-local")
    maven(url = "https://repo.gradle.org/gradle/libs-releases-local")
    maven(url = "https://repo.gradle.org/gradle/libs-snapshots")
    maven(url = "https://repo.gradle.org/gradle/ext-snapshots-local")
    maven(url = "https://repo.gradle.org/gradle/libs-snaphots-local")
}

dependencies {
    compile(project(":test-fixtures"))
    compile("org.xmlunit:xmlunit-matchers:2.5.1")
    implementation("org.gradle:sample-check:0.1.0")
}

val customInstallation: Copy by rootProject.tasks
tasks {
    "test"(Test::class) {
        dependsOn(customInstallation)
        systemProperty("integTest.gradleHomeDir", customInstallation.destinationDir.parentFile.canonicalPath)
        inputs.dir("$rootDir/samples")
    }
}

withParallelTests()