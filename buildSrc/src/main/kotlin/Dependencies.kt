import org.gradle.api.JavaVersion

object Versions {
    val jvmTarget = JavaVersion.VERSION_1_8.toString()
    const val app = "1.0.9"

    const val kotlin = "1.4.10"
    const val compose = "0.2.0-build124"
    const val logback = "1.2.3"
}

@Suppress("unused")
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
