package com.example.mobilebankingcompose.screen.select_language.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilebankingcompose.R
import com.example.mobilebankingcompose.core.ui.theme.Cerulean
import com.example.mobilebankingcompose.core.ui.theme.SelectItemColor
import com.example.mobilebankingcompose.core.ui.theme.TextGray
import com.example.entity.local.LocationHelper
import com.example.core.module.main.Language


@Composable
fun SelectScreenItem(
    @DrawableRes id: Int,
    text: String,
    isHideRadioBottom: Boolean = false,
    isSelected: Boolean = false,
    language: com.example.core.module.main.Language? = null,
    buttonClick: () -> Unit = {},
    click: () -> Unit = {}
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(73.dp)
        .background(Color.Transparent)
        .padding(start = 15.dp, end = 15.dp, bottom = 8.dp), colors = CardDefaults.cardColors(
        containerColor = SelectItemColor,
    ), shape = RoundedCornerShape(18.dp), onClick = {
        click.invoke()
    }) {
        val context = LocalContext.current
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxHeight()
        ) {
            Image(
                painter = painterResource(id),
                contentDescription = null,
                modifier = Modifier
                    .size(46.dp)
                    .padding(start = 15.dp)
            )
            Text(
                text = text, modifier = Modifier.padding(start = 15.dp), style = TextStyle(
                    fontSize = 14.sp, color = Color.White, fontFamily = FontFamily(
                        Font(
                            resId = R.font.circe_rounded_regular_bold
                        )
                    )
                )
            )
            Spacer(
                Modifier
                    .weight(1f)
                    .width(0.dp)
            )

            if (isHideRadioBottom) {
                RadioButton(
                    isSelected, {
                        buttonClick.invoke()
                        com.example.entity.local.LocationHelper.setLocation(context, language!!.country)
                    }, colors = RadioButtonColors(
                        unselectedColor = TextGray,
                        selectedColor = Cerulean,
                        disabledUnselectedColor = TextGray,
                        disabledSelectedColor = TextGray,
                    ), modifier = Modifier.padding(end = 15.dp)
                )
            }
        }
    }
}