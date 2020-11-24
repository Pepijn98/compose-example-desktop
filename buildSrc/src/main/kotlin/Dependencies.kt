@file:Suppress("MemberVisibilityCanBePrivate", "unused")

import org.gradle.api.JavaVersion

object BuildInfo {
    const val name = "Pepijn98"
    const val group = "dev.vdbroek"
    const val version = "1.0.14"
    const val description = "Compose for Desktop Example App"
    const val copyright = "© 2020 Pepijn van den Broek. All rights reserved."
    const val vendor = "Pepijn van den Broek"

    val mainClass = "$group.${name.toLowerCase()}.MainKt"
}

object Versions {
    val jvmTarget = JavaVersion.VERSION_1_8.toString()

    const val kotlin = "1.4.10"
    const val compose = "0.2.0-build124"
    const val logback = "1.2.3"
}

object Deps {
    const val systemTray = "com.dorkbox:SystemTray:3.17"

    object Kotlin {
        // TODO : Add deps
    }

    object Compose {
        // TODO : Add deps
    }

    object Logging {
        const val slf4j = "org.slf4j:slf4j-api:1.7.30"

        object Logback {
            const val core = "ch.qos.logback:logback-core:${Versions.logback}"
            const val classic = "ch.qos.logback:logback-classic:${Versions.logback}"
        }
    }
}
