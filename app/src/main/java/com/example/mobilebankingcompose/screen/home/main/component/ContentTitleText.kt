package com.example.mobilebankingcompose.screen.home.main.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilebankingcompose.R
import com.example.mobilebankingcompose.core.ui.theme.TextGray

@Composable
fun ContentTitleText(
    text: String = "",
    bottomPadding:Dp = 0.dp
) {
    Text(
        text = text, style = TextStyle(
            color = TextGray,
            fontFamily = FontFamily(Font(R.font.euclid_circular_regular)),
            fontSize = 16.sp,
        ),
        modifier = Modifier.padding(bottom = bottomPadding)
    )
}