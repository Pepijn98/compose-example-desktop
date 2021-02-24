package dev.vdbroek.pepijn98.ui.utils

import androidx.compose.desktop.AppWindow
import androidx.compose.desktop.WindowEvents
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.MenuBar
import dev.vdbroek.pepijn98.ui.AppTheme
import java.awt.image.BufferedImage

class Dialog {
    var isOpen by mutableStateOf(false)

    @Composable
    fun build(
        icon: ImageVector,
        title: String,
        message: String,
        buttons: Pair<Button, Button>,
        iconTint: Color = MaterialTheme.colors.onBackground,
        size: IntSize = IntSize(400, 150),
        location: IntOffset = IntOffset.Zero,
        centered: Boolean = true,
        image: BufferedImage? = null,
        menuBar: MenuBar? = null,
        undecorated: Boolean = false,
        events: WindowEvents = WindowEvents(),
        onDismissRequest: (() -> Unit) = { close() },
    ) {
        val dialog = remember {
            AppWindow(
                title = title,
                size = size,
                location = location,
                centered = centered,
                icon = image,
                menuBar = menuBar,
                undecorated = undecorated,
                events = events,
                onDismissRequest = onDismissRequest
            )
        }

        dialog.window.isResizable = false

        DisposableEffect(Unit) {
            dialog.show {
                AppTheme {
                    Box(
                        modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize().padding(10.dp),
                        ) {
                            Box(
                                modifier = Modifier.wrapContentSize().align(Alignment.Top).padding(end = 10.dp)
                            ) {
                                Icon(
                                    modifier = Modifier.size(80.dp),
                                    imageVector = icon,
                                    contentDescription = "",
                                    tint = iconTint
                                )
                            }
                            Box(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Top
                                ) {
                                    Text(
                                        modifier = Modifier.padding(bottom = 5.dp),
                                        text = title,
                                        color = MaterialTheme.colors.onBackground,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = message,
                                        color = MaterialTheme.colors.onBackground
                                    )
                                }
                                Row(
                                    modifier = Modifier.wrapContentSize().align(Alignment.BottomEnd)
                                ) {
                                    val (btnLeft, btnRight) = buttons
                                    DialogButton(btnLeft.type, btnLeft.action)
                                    DialogButton(btnRight.type, btnRight.action, true)
                                }
                            }
                        }
                    }
                }
            }

            onDispose {
                if (!dialog.isClosed) {
                    dialog.close()
                }
            }
        }
    }

    @Composable
    fun DialogButton(type: ButtonType, onClick: () -> Unit, last: Boolean = false) {
        val modifier = if (last) Modifier.padding(end = 0.dp) else Modifier.padding(end = 10.dp)
        when (type) {
            ButtonType.OK -> Button(onClick, modifier) {
                Text("Ok")
            }
            ButtonType.CONFIRM -> Button(onClick, modifier) {
                Text("Confirm")
            }
            ButtonType.CLOSE -> TextButton(onClick, modifier) {
                Text("Close", color = MaterialTheme.colors.onBackground)
            }
            ButtonType.QUIT -> TextButton(onClick, modifier) {
                Text("Quit", color = MaterialTheme.colors.onBackground)
            }
        }
    }

    fun show() {
        isOpen = true
    }

    fun close() {
        isOpen = false
    }

    data class Button(
        val type: ButtonType,
        val action: () -> Unit
    )

    enum class ButtonType {
        OK,
        CONFIRM,
        CLOSE,
        QUIT
    }
}
