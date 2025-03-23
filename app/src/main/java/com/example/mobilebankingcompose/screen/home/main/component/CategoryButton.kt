package com.example.mobilebankingcompose.screen.home.main.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilebankingcompose.R
import com.example.mobilebankingcompose.core.ui.theme.Cerulean
import com.example.mobilebankingcompose.core.ui.theme.ComponentColor

@Composable
fun CategoryButton(
    @DrawableRes id: Int,
    text: String = ""
) {
    Box(
        modifier = Modifier
            .background(ComponentColor, shape = RoundedCornerShape(8.dp))
            .height(100.dp)
            .width(85.dp)
    ) {

        Column(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 9.dp)
                .fillMaxSize()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 7.dp)
                    .size(30.dp),
                tint = Cerulean
            )
            Spacer(Modifier.height(7.dp))
            Text(
                text = text, style = TextStyle(
                    color = Color.White,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.euclid_circular_regular))
                ), modifier = Modifier,
                textAlign = TextAlign.Center
            )
        }
    }
}