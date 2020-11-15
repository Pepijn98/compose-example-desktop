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
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.MenuBar
import dev.vdbroek.pepijn98.ui.AppColors
import dev.vdbroek.pepijn98.ui.AppTheme
import java.awt.image.BufferedImage
import java.util.*

@Suppress("MemberVisibilityCanBePrivate")
class Dialog {
    var isOpen by mutableStateOf(false)

    @Composable
    fun create(
        icon: VectorAsset,
        title: String,
        message: String,
        buttons: EnumSet<Button>,
        size: IntSize = IntSize(400, 150),
        location: IntOffset = IntOffset.Zero,
        centered: Boolean = true,
        image: BufferedImage? = null,
        menuBar: MenuBar? = null,
        undecorated: Boolean = false,
        events: WindowEvents = WindowEvents(),
        onOkClick: () -> Unit = { close() },
        onConfirmClick: () -> Unit = { close() },
        onCloseClick: () -> Unit = { close() },
        onQuitClick: () -> Unit = { close() },
        onDismissEvent: (() -> Unit) = { close() },
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
                onDismissEvent = onDismissEvent
            )
        }

        onActive {
            dialog.show {
                AppTheme {
                    Row(
                        modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background),
                    ) {
                        Box(
                            modifier = Modifier.align(Alignment.Top).padding(10.dp)
                        ) {
                            Icon(
                                asset = icon.copy(defaultHeight = 80.dp, defaultWidth = 80.dp),
                                tint = MaterialTheme.colors.onBackground
                            )
                        }
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier.padding(top = 10.dp).fillMaxSize(),
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
                                modifier = Modifier.wrapContentSize().padding(bottom = 5.dp).align(Alignment.BottomEnd)
                            ) ButtonRow@{
                                for (button in buttons) {
                                    val modifier = Modifier.padding(end = 5.dp)
                                    when (button) {
                                        Button.OK -> Button(onOkClick, modifier) { Text("Ok") }
                                        Button.CONFIRM -> Button(onConfirmClick, modifier) { Text("Confirm") }
                                        Button.CLOSE -> Button(
                                            onCloseClick,
                                            modifier,
                                            colors = ButtonConstants.defaultButtonColors(
                                                backgroundColor = AppColors.danger,
                                                contentColor = Color.White
                                            )
                                        ) {
                                            Text("Close")
                                        }
                                        Button.QUIT -> Button(
                                            onQuitClick,
                                            modifier,
                                            colors = ButtonConstants.defaultButtonColors(
                                                backgroundColor = AppColors.danger,
                                                contentColor = Color.White
                                            )
                                        ) {
                                            Text("Quit")
                                        }
                                        else -> return@ButtonRow
                                    }
                                }
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

    fun open() {
        isOpen = true
    }

    fun close() {
        isOpen = false
    }

    enum class Button {
        OK,
        CONFIRM,
        CLOSE,
        QUIT
    }
}
