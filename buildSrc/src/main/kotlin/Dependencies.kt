import org.gradle.api.JavaVersion

object Versions {
    val jvmTarget = JavaVersion.VERSION_1_8.toString()
    const val kotlin = "1.4.10"
    const val compose = "0.1.0-build113"
    const val app = "1.0.2"
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
}
