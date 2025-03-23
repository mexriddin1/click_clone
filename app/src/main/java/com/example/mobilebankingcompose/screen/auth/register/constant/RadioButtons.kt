package com.example.mobilebankingcompose.screen.auth.register.constant

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilebankingcompose.R
import com.example.mobilebankingcompose.core.ui.theme.Cerulean
import com.example.mobilebankingcompose.core.ui.theme.TextGray

@Composable
fun RadioButtons(change: (Int) -> Unit) {
    var gender by remember { mutableIntStateOf(0) }
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(end = 20.dp)
            .height(30.dp)
    ) {
        RadioButton(
            selected = gender == 0,
            {
                gender = 0; change.invoke(0)
            },
            colors = RadioButtonColors(
                unselectedColor = TextGray,
                selectedColor = Cerulean,
                disabledUnselectedColor = TextGray,
                disabledSelectedColor = TextGray,
            ),
        )

        Text(
            text = stringResource(R.string.woman),
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.circe_rounded_regular)),
                fontSize = 16.sp,
                color = Color.White
            ),
            modifier = Modifier.padding(end = 10.dp)
        )

        RadioButton(
            selected = gender == 1,
            {
                gender = 1; change.invoke(1)
            },
            colors = RadioButtonColors(
                unselectedColor = TextGray,
                selectedColor = Cerulean,
                disabledUnselectedColor = TextGray,
                disabledSelectedColor = TextGray
            ),
        )
        Text(
            text = stringResource(R.string.man),
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.circe_rounded_regular)),
                fontSize = 16.sp,
                color = Color.White
            ),
        )
    }
}