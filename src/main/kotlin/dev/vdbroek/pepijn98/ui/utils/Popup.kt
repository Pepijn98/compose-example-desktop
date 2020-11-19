package dev.vdbroek.pepijn98.ui.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import dev.vdbroek.pepijn98.ui.AppColors
import dev.vdbroek.pepijn98.ui.shapes

@Suppress("unused", "MemberVisibilityCanBePrivate")
object Popup {

    enum class Type {
        INFO,
        SUCCESS,
        WARNING,
        ERROR
    }

    object State {
        var info by mutableStateOf(false)
        var success by mutableStateOf(false)
        var warning by mutableStateOf(false)
        var error by mutableStateOf(false)

        fun enable(type: Type) {
            when (type) {
                Type.INFO -> info = true
                Type.SUCCESS -> success = true
                Type.WARNING -> warning = true
                Type.ERROR -> error = true
            }
        }

        fun disable(type: Type) {
            when (type) {
                Type.INFO -> info = false
                Type.SUCCESS -> success = false
                Type.WARNING -> warning = false
                Type.ERROR -> error = false
            }
        }
    }

    @Composable
    private fun Default(text: String, type: Type) {
        val icon: ImageVector
        val background: Color
        val onBackground = if (type == Type.WARNING) AppColors.darkSurface else Color.White

        when (type) {
            Type.INFO -> {
                icon = Icons.Rounded.Info
                background = AppColors.info
            }
            Type.SUCCESS -> {
                icon = Icons.Rounded.CheckCircle
                background = AppColors.success
            }
            Type.WARNING -> {
                icon = Icons.Rounded.Warning
                background = AppColors.warning
            }
            Type.ERROR -> {
                icon = Icons.Rounded.Error
                background = AppColors.danger
            }
        }

        Popup(
            alignment = Alignment.TopStart,
            offset = IntOffset(10, 10),
            isFocusable = true
        ) {
            Card(
                elevation = 4.dp,
                shape = shapes.medium,
                backgroundColor = background
            ) {
                Row(
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        imageVector = icon,
                        tint = onBackground
                    )
                    Text(
                        text = text,
                        color = onBackground
                    )
                    IconButton(
                        modifier = Modifier.padding(start = 10.dp).size(15.dp).clip(RoundedCornerShape(percent = 50)).clipToBounds(),
                        onClick = {
                            State.disable(type)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            tint = onBackground
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun Info(text: String) {
        Default(text, Type.INFO)
    }

    @Composable
    fun Success(text: String) {
        Default(text, Type.SUCCESS)
    }

    @Composable
    fun Warning(text: String) {
        Default(text, Type.WARNING)
    }

    @Composable
    fun Error(text: String) {
        Default(text, Type.ERROR)
    }
}