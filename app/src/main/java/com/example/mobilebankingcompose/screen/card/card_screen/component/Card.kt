package com.example.mobilebankingcompose.screen.card.card_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilebankingcompose.R
import com.example.mobilebankingcompose.core.ui.theme.Cerulean
import com.example.mobilebankingcompose.core.ui.theme.ComponentColor
import com.example.mobilebankingcompose.core.ui.theme.ComponentRadius
import com.example.mobilebankingcompose.core.ui.theme.TextGray
import com.example.core.module.main.CardType
import com.example.mobilebankingcompose.screen.home.main.component.MainSumText

@Composable
fun Card(
    cardType: com.example.core.module.main.CardType,
    cardNumber: String,
    cardName: String,
    sum: String,
    year: String,
    numberIsShow: Boolean = false,
    isMainCard: Boolean = false,
    clickCardName: () -> Unit = {},
    clickCopy: (String) -> Unit = {},
    isSelection: Boolean = true,
    click: () -> Unit = {}
) {


    Column(
        modifier = Modifier
            .padding(horizontal = if (isSelection) 16.dp else 9.dp, vertical = 8.dp)
            .fillMaxWidth()
            .height(210.dp)
            .border(
                1.dp,
                color = if (isMainCard) Cerulean else Color.Transparent,
                shape = ComponentRadius.regular
            )
            .background(ComponentColor, shape = ComponentRadius.regular)
            .padding(16.dp)
            .clickable(
                indication = null, interactionSource = remember { MutableInteractionSource() }
            ) {
                click.invoke()
            }
    ) {


        Image(
            painter = painterResource(cardType.id),
            contentDescription = null,
            modifier = Modifier.height(25.dp)
        )

        Spacer(Modifier.height(25.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                cardName, style = TextStyle(
                    color = TextGray,
                    fontFamily = FontFamily(Font(R.font.euclid_circular_regular)),
                    fontSize = 22.sp
                ), modifier = Modifier.clickable(
                    indication = null, interactionSource = remember { MutableInteractionSource() }
                ) {
                    clickCardName.invoke()
                }

            )

            if (isSelection) {
                Icon(
                    painter = painterResource(R.drawable.star),
                    contentDescription = null,
                    modifier = Modifier
                        .size(22.dp),
                    tint = if (!isMainCard) Color.Transparent else Cerulean
                )
            }
        }

        Spacer(Modifier.height(5.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    if (!numberIsShow) maskCardNumber(cardNumber) else cardNumber,
                    style = TextStyle(
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.euclid_circular_regular)),
                        fontSize = 25.sp
                    )
                )
                if (isSelection){
                    Icon(
                        painter = painterResource(R.drawable.copy),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .size(21.dp)
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                clickCopy.invoke(cardNumber)
                            },
                        tint = Cerulean
                    )
                }
            }
            if (isSelection){
                Text(
                    year, style = TextStyle(
                        color = TextGray,
                        fontFamily = FontFamily(Font(R.font.euclid_circular_regular)),
                        fontSize = 25.sp
                    )
                )
            }
        }
        Spacer(Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MainSumText(sum, true, textSize = 30)

            Image(
                painter = painterResource(cardType.logo),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

fun maskCardNumber(cardNumber: String): String {
    val regex = Regex("\\b(\\d{4})\\s(\\d{2})\\d{2}\\s\\d{4}\\s(\\d{4})\\b")
    return regex.replace(cardNumber) {
        "${it.groupValues[1]} ${it.groupValues[2]}** **** ${it.groupValues[3]}"
    }
}