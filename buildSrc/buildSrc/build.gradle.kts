plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    jcenter()
}

sourceSets.main {
    java {
        setSrcDirs(setOf(projectDir.parentFile.resolve("src/main/kotlin")))
        include("Dependencies.kt")
    }
}