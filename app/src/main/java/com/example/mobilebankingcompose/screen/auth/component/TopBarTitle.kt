package com.example.mobilebankingcompose.screen.auth.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mobilebankingcompose.core.ui.theme.ComponentTextStyle

@Composable
fun TopAppTitle(
    @StringRes titleId: Int
) {
    Text(
        text = stringResource(titleId),
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 40.dp),
        textAlign = TextAlign.Center,
        style = ComponentTextStyle.TopAppBarText
    )
}

@Composable
fun TopAppTitleCode(
    @StringRes titleId: Int
) {
    Text(
        text = stringResource(titleId),
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 40.dp),
        textAlign = TextAlign.Center,
        style = ComponentTextStyle.TopAppBarCodeText
    )
}