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
import androidx.compose.ui.graphics.imageFromResource
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
import java.util.*
import javax.imageio.ImageIO

lateinit var dialog: Dialog

fun main() = Window(
    title = "Pepijn98",
    size = IntSize(700, 450),
    icon = getWindowIcon()
) {
    var count by remember { mutableStateOf(0) }
    val appIcon = remember { getWindowIcon() }

    AppManager.focusedWindow?.setIcon(appIcon)

    dialog = Dialog()

    val resource = ClassLoader.getSystemResource("version.properties")
    val properties = Properties()
    properties.load(resource.openStream())

    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background),
            alignment = Alignment.Center
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                Button(onClick = { count++ }) {
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
                    imageVector = if (ThemeState.isDark) Icons.Rounded.Bedtime else Icons.Rounded.WbSunny,
                    tint = MaterialTheme.colors.onBackground
                )
            }
            Text(
                modifier = Modifier.align(Alignment.BottomEnd).padding(10.dp),
                text = "v${properties.getProperty("version")}",
                color = MaterialTheme.colors.onBackground
            )
        }

        if (Popup.isOpen(Popup.Type.SUCCESS)) {
            Popup.Success("This is just a test")
        }

        if (dialog.isOpen) {
            dialog.build(
                icon = Icons.Rounded.Info,
                title = "Test",
                message = "This is just a test",
                buttons = Pair(
                    Dialog.Button(
                        type = Dialog.ButtonType.CLOSE,
                        action = {
                            dialog.close()
                        }
                    ),
                    Dialog.Button(
                        type = Dialog.ButtonType.CONFIRM,
                        action = {
                            dialog.close()
                        }
                    )
                )
            )
        }
    }

    onActive {
        val tray = SystemTray(appIcon)

        onDispose {
            tray.shutdown()
        }
    }
}

fun SystemTray(appIcon: BufferedImage): SystemTray {
    return SystemTray.get().apply {
        setImage(appIcon)
        status = "Pepijn98"
        menu.addMany(
            MenuItem("Open Popup") {
                Popup.show(Popup.Type.SUCCESS)
            },
            MenuItem("Open Dialog") {
                dialog.show()
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
        val icon = ClassLoader.getSystemResource("images/icon.png")
        imageFromResource("")
        image = ImageIO.read(icon.openStream())
    } catch (e: Exception) {
        // image file does not exist
    }

    if (image == null) {
        image = BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)
    }

    return image
}
