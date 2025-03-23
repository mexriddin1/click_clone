package com.example.mobilebankingcompose.screen.home.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilebankingcompose.R
import com.example.mobilebankingcompose.core.ui.theme.ComponentColor
import com.example.mobilebankingcompose.core.ui.theme.ComponentRadius
import com.example.mobilebankingcompose.core.ui.theme.ComponentSize
import com.example.mobilebankingcompose.core.ui.theme.InputHint


@Composable
fun RowScope.SearchTextField(
    click: () -> Unit
) {
    Row(
        modifier = Modifier
            .height(ComponentSize.AuthConstantHeight)
            .weight(1f)
            .background(ComponentColor, shape = ComponentRadius.regular)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                click.invoke()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.search),
            contentDescription = null,
            modifier = Modifier.size(38.dp).padding(horizontal = 8.dp)
        )
        Text(text = stringResource(R.string.search), style = TextStyle(
            color = InputHint,
            fontSize = 19.sp,
            fontFamily = FontFamily(Font(R.font.euclid_circular_regular))
        ))
    }
}