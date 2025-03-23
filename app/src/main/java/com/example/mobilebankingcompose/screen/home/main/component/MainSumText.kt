package com.example.mobilebankingcompose.screen.home.main.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilebankingcompose.R

@Composable
fun MainSumText(
    text: String = "",
    state: Boolean,
    textSize: Int = 36,
    textSum:Int = 23,
    padding:Int = 3
) {
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = if (state) text else "*****", style = TextStyle(
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.euclid_circular_semibold)),
                fontSize = textSize.sp
            ),
            modifier = Modifier.padding(0.dp)
        )
        if (state) {
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "so'm", style = TextStyle(
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.euclid_circular_regular)),
                    fontSize = textSum.sp
                ), modifier = Modifier.padding(bottom = padding.dp)
            )
        }
    }
}