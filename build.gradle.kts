import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm") version Versions.kotlin
    id("org.jetbrains.compose") version Versions.compose
}

group = "dev.vdbroek"
version = Versions.app

repositories {
    jcenter()
    maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(compose.desktop.all)
    implementation(compose.foundation)
    implementation(compose.material)
    implementation(compose.runtime)
    implementation(compose.ui)
    implementation(compose.materialIconsExtended)

    testImplementation(kotlin("test-junit"))
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
        javaHome = System.getenv("JDK_15") // Path to Amazon Corretto 15

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = rootProject.name
            version = Versions.app
            description = "Compose for Desktop Example App"
            vendor = "Pepijn van den Broek"
        }
    }
}
