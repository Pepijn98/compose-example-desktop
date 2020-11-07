import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm") version Versions.kotlin
    id("org.jetbrains.compose") version Versions.compose
}

group = "dev.vdbroek"
version = "1.0.0"

repositories {
    jcenter()
    maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    testImplementation(kotlin("test-junit"))
    implementation(compose.desktop.all)
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = Versions.jvmTarget
    }

    compileTestKotlin {
        kotlinOptions.jvmTarget = Versions.jvmTarget
    }

    test {
        useJUnit()
    }
}

compose.desktop {
    application {
        mainClass = "$group.${rootProject.name.toLowerCase()}.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = rootProject.name
        }
    }
}
