package com.example.mobilebankingcompose.screen.home.transfer.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.mobilebankingcompose.core.ui.theme.ComponentColor
import com.example.mobilebankingcompose.core.ui.theme.TextGray
import com.example.mobilebankingcompose.screen.card.card_screen.component.maskCardNumber

@Composable
fun UserItem(
    name: String = "",
    userNumber: String = "",
    click: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                ComponentColor, shape = RoundedCornerShape(8.dp)
            )
            .height(70.dp)
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(
                indication = null, interactionSource = remember { MutableInteractionSource() }
            ) {
                click.invoke()
            }

    ) {
        Image(
            painter = painterResource(R.drawable.user),
            contentDescription = null,
            modifier = Modifier.size(45.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column {
            Text(
                text = name, style = TextStyle(
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.euclid_circular_medium)),
                    fontSize = 17.sp
                ), modifier = Modifier.padding(bottom = 2.dp)
            )
            Text(
                text = maskCardNumber(userNumber),
                style = TextStyle(
                    color = TextGray,
                    fontFamily = FontFamily(Font(R.font.euclid_circular_regular)),
                    fontSize = 15.sp
                )
            )
        }
    }
}