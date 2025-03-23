package com.example.mobilebankingcompose.screen.pic_code.constant

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mobilebankingcompose.core.ui.theme.ComponentRadius
import com.example.mobilebankingcompose.core.ui.theme.ComponentTextStyle
import com.example.mobilebankingcompose.core.ui.theme.DarkGrayColor

@Composable
fun CodeItem(
    number: String? = null,
    @DrawableRes id: Int? = null,
    longClick: () -> Unit = {},
    click: (Int?, String?) -> Unit,
    size: Dp = 0.dp
) {
    var isPressed by remember { mutableStateOf(false) }
    val offsetY by animateDpAsState(
        targetValue = if (isPressed) 1.5.dp else 0.dp,
        animationSpec = tween(durationMillis = 100), label = ""
    )
    Column(
        modifier = if (number != null) Modifier
            .offset(y = offsetY) else Modifier
    ) {
        Box(
            modifier = Modifier
                .requiredSize(85.dp, 85.dp)
                .shadow(
                    if (number == null) 0.dp else 3.dp, shape = ComponentRadius.medium
                )
                .background(
                    if (id != null) Color.Transparent else DarkGrayColor,
                    shape = ComponentRadius.medium
                )
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            isPressed = true
                            try {
                                awaitRelease()
                            } finally {
                                isPressed = false
                            }
                        },
                        onLongPress = {
                            longClick.invoke()
                        },
                        onTap = {
                            click.invoke(id, number)
                        }
                    )
                }

        ) {
            if (id != null) {
                Image(
                    painter = painterResource(id),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(size)
                )
            }

            if (number != null) {
                Text(
                    text = number,
                    modifier = Modifier.align(Alignment.Center),
                    style = ComponentTextStyle.CodeTextStyle
                )
            }
        }
        Spacer(Modifier.height(12.dp))
    }
}
