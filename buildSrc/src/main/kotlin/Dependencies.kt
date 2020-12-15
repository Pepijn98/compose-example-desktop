@file:Suppress("MemberVisibilityCanBePrivate", "unused")

import org.gradle.api.JavaVersion

object BuildInfo {
    const val name = "Pepijn98"
    const val group = "dev.vdbroek"
    const val version = "1.0.15"
    const val release = "0"
    const val description = "Compose for Desktop Example App"
    const val copyright = License.BASIC
    const val vendor = "Pepijn van den Broek"

    val mainClass = "$group.${name.toLowerCase()}.App"
}
object Versions {
    val jvmTarget = JavaVersion.VERSION_1_8.toString()

    const val kotlin = "1.4.21"
    // https://maven.pkg.jetbrains.space/public/p/compose/dev/org/jetbrains/compose/desktop/
    const val compose = "0.3.0-build135"
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

object License {
    const val BASIC = "Copyright © 2020 Pepijn van den Broek. All rights reserved."
    val MIT = """
        MIT License
    
        Copyright (c) 2020 Pepijn van den Broek
        
        Permission is hereby granted, free of charge, to any person obtaining a copy
        of this software and associated documentation files (the "Software"), to deal
        in the Software without restriction, including without limitation the rights
        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
        copies of the Software, and to permit persons to whom the Software is
        furnished to do so, subject to the following conditions:
        
        The above copyright notice and this permission notice shall be included in all
        copies or substantial portions of the Software.
        
        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
        SOFTWARE.
    """.trimIndent()

    object Type {
        const val MIT = "MIT"
    }
}
