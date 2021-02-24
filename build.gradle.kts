import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import java.util.Properties

plugins {
    kotlin("jvm") version Versions.kotlin
    id("org.jetbrains.compose") version Versions.compose
}

group = BuildInfo.group
version = BuildInfo.version

repositories {
    jcenter()
    mavenCentral()
    maven(url = "https://jitpack.io")
    maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    // api(compose.runtime)
    // api(compose.foundation)
    // api(compose.material)
    api(compose.desktop.common)
    api(compose.desktop.currentOs)
    // api(compose.desktop.linux_x64)
    // api(compose.desktop.windows_x64)
    // api(compose.desktop.macos_arm64)
    // api(compose.desktop.macos_x64)
    api(compose.materialIconsExtended)

    implementation(Deps.systemTray)

    implementation(Deps.Logging.slf4j)
    implementation(Deps.Logging.Logback.core)
    implementation(Deps.Logging.Logback.classic)

    // Neither of these nor the build in compose notifier work on my linux environment
    // I don't know if it's because of compose or if they just don't support it or maybe I configured something badly
    implementation("com.github.PlusHaze:TrayNotification:5393c3a54f")
    implementation("org.controlsfx:controlsfx:11.0.3")

    testImplementation(kotlin("test-junit"))
}

tasks {
    val createProperties: Task by creating {
        doLast {
            File("$buildDir/resources/main/build-info.properties").bufferedWriter().use {
                val p = Properties()
                p.setProperty("name", BuildInfo.name)
                p.setProperty("group", BuildInfo.group)
                p.setProperty("version", BuildInfo.version)
                p.setProperty("description", BuildInfo.description)
                p.setProperty("copyright", BuildInfo.copyright)
                p.setProperty("vendor", BuildInfo.vendor)
                p.setProperty("mainClass", BuildInfo.mainClass)
                p.store(it, null)
            }
        }
    }

    compileKotlin {
        kotlinOptions.jvmTarget = Versions.jvmTarget
    }

    compileTestKotlin {
        kotlinOptions.jvmTarget = Versions.jvmTarget
    }

    test {
        useJUnit()
    }

    build {
        dependsOn(createProperties)
    }
}

compose.desktop {
    application {
        mainClass = BuildInfo.mainClass
        javaHome = System.getenv("JDK_15") // Path to Amazon Corretto 15

        nativeDistributions {
            targetFormats(
                // Linux
                TargetFormat.Deb,
                TargetFormat.AppImage,
                // Mac
                TargetFormat.Dmg,
                // Windows
                TargetFormat.Msi,
                TargetFormat.Exe
            )

            packageName = BuildInfo.name
            description = BuildInfo.description
            copyright = BuildInfo.copyright
            vendor = BuildInfo.vendor
            packageVersion = BuildInfo.version

            val iconsRoot = project.file("./src/main/resources/images/icons")
            macOS {
                iconFile.set(iconsRoot.resolve("icon.icns"))

                // packageIdentifier = "${BuildInfo.group}.${BuildInfo.name}"
            }

            linux {
                iconFile.set(iconsRoot.resolve("icon.png"))

                appRelease = BuildInfo.release
                appCategory = "Utility"
                debMaintainer = BuildInfo.vendor
                menuGroup = BuildInfo.name
                rpmLicenseType = License.Type.MIT
            }

            windows {
                iconFile.set(iconsRoot.resolve("icon.ico"))

                menuGroup = BuildInfo.name
                upgradeUuid = "d5d747e9-2ff0-4b46-b295-fb9390008309"
            }
        }
    }
}
