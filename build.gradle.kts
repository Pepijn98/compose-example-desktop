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
    mavenCentral()
    maven(url = "https://jitpack.io")
    maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(compose.desktop.all)
    implementation(compose.materialIconsExtended)

    implementation(Deps.systemTray)

    // Neither of these nor the build in compose notifier work on my linux environment
    // I don't know if it's because of compose or if they just don't support it or maybe I configured something badly
    implementation("com.github.PlusHaze:TrayNotification:5393c3a54f")
    implementation("org.controlsfx:controlsfx:11.0.3")

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

            val iconsRoot = project.file("./assets/appicon")
            macOS {
                iconFile.set(iconsRoot.resolve("icon.icns"))
            }

            windows {
                iconFile.set(iconsRoot.resolve("icon.ico"))
                menuGroup = "Pepijn98"
                upgradeUuid = "d5d747e9-2ff0-4b46-b295-fb9390008309"
            }

            linux {
                iconFile.set(iconsRoot.resolve("icon.png"))
            }
        }
    }
}
