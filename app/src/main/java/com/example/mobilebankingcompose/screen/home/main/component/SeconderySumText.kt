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
fun SecondSumText(
    text: String = "",
) {
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = text, style = TextStyle(
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.euclid_circular_semibold)),
                fontSize = 20.sp
            ),
            modifier = Modifier.padding(0.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = "so'm", style = TextStyle(
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.euclid_circular_medium)),
                fontSize = 16.sp
            ), modifier = Modifier.padding(bottom = 2.dp)
        )
    }
}