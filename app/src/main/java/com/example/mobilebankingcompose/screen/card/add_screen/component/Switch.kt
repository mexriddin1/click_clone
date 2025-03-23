package com.example.mobilebankingcompose.screen.card.add_screen.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mobilebankingcompose.core.ui.theme.Cerulean

@Composable
fun CustomSwitch(
    checked: Boolean, onCheckedChange: (Boolean) -> Unit, modifier: Modifier = Modifier
) {

    val offsetX by animateDpAsState(
        targetValue = if (checked) 15.dp else 0.dp,
        animationSpec = tween(durationMillis = 100),
        label = ""
    )

    Box(
        modifier = modifier
            .padding(end = 7.dp)
            .width(35.dp)
    ) {
        Box(
            modifier = modifier
                .width(35.dp)
                .height(15.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(if (checked) Cerulean.copy(alpha = 0.5f) else Color.White.copy(alpha = 0.3f))
                .clickable { onCheckedChange(!checked) }
                .padding(start = 20.dp)
                .align(Alignment.CenterStart)
        )

        Box(
            modifier = Modifier
                .size(20.dp)
                .offset(x = offsetX)
                .clip(CircleShape)
                .background(if (checked) Cerulean else Color.White.copy(alpha = 0.75f))
                .align(Alignment.CenterStart)
        )
    }

}
