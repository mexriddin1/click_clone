package com.example.mobilebankingcompose.screen.auth.component

import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.mobilebankingcompose.core.ui.theme.ComponentTextStyle


// Modifier.padding(start = 15.dp, top = 20.dp, bottom = 10.dp)

@Composable
fun InputTitle(
    @StringRes string: Int,
    modifier: Modifier
) {
    Text(
        modifier = modifier,
        text = stringResource(string),
        style = ComponentTextStyle.InputTitle
    )
}