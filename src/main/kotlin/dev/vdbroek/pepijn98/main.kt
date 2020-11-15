package dev.vdbroek.pepijn98

import androidx.compose.desktop.AppManager
import androidx.compose.desktop.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bedtime
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import dev.vdbroek.pepijn98.ui.AppTheme
import dev.vdbroek.pepijn98.ui.ThemeState
import dev.vdbroek.pepijn98.ui.utils.Dialog
import dev.vdbroek.pepijn98.ui.utils.Popup
import dev.vdbroek.pepijn98.utils.addMany
import dorkbox.systemTray.MenuItem
import dorkbox.systemTray.SystemTray
import java.awt.image.BufferedImage
import java.io.File
import java.util.*
import javax.imageio.ImageIO

fun main() = Window(
    title = "Pepijn98",
    size = IntSize(700, 450),
    icon = getWindowIcon()
) {
    var count by remember { mutableStateOf(0) }
    val appIcon = remember { getWindowIcon() }

    val dialog = Dialog()

    onActive {
        val tray = SystemTray(appIcon)

        onDispose {
            tray.shutdown()
        }
    }

    AppManager.focusedWindow?.setIcon(appIcon)

    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background),
            alignment = Alignment.Center
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                Button(onClick = { count++; dialog.open() }) {
                    Text(text = if (count == 0) "Click me!" else "Clicked ${count}!")
                }
                Button(
                    enabled = count > 0,
                    onClick = {
                        count = 0
                    }
                ) {
                    Text(text = "Reset")
                }
            }
            IconButton(
                modifier = Modifier.align(Alignment.BottomStart),
                onClick = {
                    ThemeState.isDark = !ThemeState.isDark
                }
            ) {
                Icon(
                    modifier = Modifier.padding(10.dp),
                    asset = if (ThemeState.isDark) Icons.Rounded.Bedtime else Icons.Rounded.WbSunny,
                    tint = MaterialTheme.colors.onBackground
                )
            }
        }

        if (dialog.isOpen) {
            dialog.create(
                icon = Icons.Rounded.Info,
                title = "Test",
                message = "This is just a test",
                buttons = EnumSet.of(Dialog.Button.OK, Dialog.Button.CLOSE)
            )
        }

        if (Popup.State.success) {
            Popup.Success("This is just a test")
        }
    }
}

fun SystemTray(appIcon: BufferedImage): SystemTray {
    return SystemTray.get().apply {
        setImage(appIcon)
        status = "Pepijn98"
        menu.addMany(
            MenuItem("Testing") {
                Popup.State.enable(Popup.Type.SUCCESS)
            },
            MenuItem("Quit") {
                AppManager.exit()
            }
        )
    }
}

fun getWindowIcon(): BufferedImage {
    var image: BufferedImage? = null
    try {
        val file = File("assets/appicon/icon.png")
        image = ImageIO.read(file)
    } catch (e: Exception) {
        // image file does not exist
    }

    if (image == null) {
        image = BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)
    }

    return image
}
