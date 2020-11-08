package dev.vdbroek.pepijn98

import androidx.compose.desktop.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import dev.vdbroek.pepijn98.ui.AppTheme
import dev.vdbroek.pepijn98.ui.ThemeState

fun main() = Window(
    title = "Pepijn98",
    size = IntSize(700, 450)
) {
    var count by remember { mutableStateOf(0) }

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
                    asset = if (ThemeState.isDark) imageFromResource("drawable/light.png") else imageFromResource("drawable/dark.png"),
                    tint = MaterialTheme.colors.onBackground
                )
            }
        }
    }
}
