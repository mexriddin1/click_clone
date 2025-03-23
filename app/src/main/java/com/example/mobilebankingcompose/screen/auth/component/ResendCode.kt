package com.example.mobilebankingcompose.screen.auth.component

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.mobilebankingcompose.core.ui.theme.ComponentTextStyle

@Composable
fun ResendButton(
    click: () -> Unit,
    @StringRes string: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
    ) {
        Text(
            text = stringResource(string),
            style = ComponentTextStyle.ResendTextStyle,
            modifier = Modifier
                .align(Alignment.Center)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    click.invoke()
                }
        )
    }
}