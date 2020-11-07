package dev.vdbroek.pepijn98

import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

fun main() = Window(
    title = "Pepijn98",
    size = IntSize(350, 150)
) {
    var count by remember { mutableStateOf(0) }

    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            alignment = Alignment.Center
        ) {
            Row (horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                Button(onClick = { count++ }) {
                    Text(text = if (count == 0) "Click me!" else "Clicked ${count}!")
                }
                Button(
                    enabled = count > 0,
                    onClick = { count = 0 }
                ) {
                    Text(text = "Reset")
                }
            }
        }
    }
}
