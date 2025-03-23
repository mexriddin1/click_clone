package com.example.mobilebankingcompose.screen.auth.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mobilebankingcompose.core.ui.theme.Cerulean
import com.example.mobilebankingcompose.core.ui.theme.ComponentTextStyle
import com.example.mobilebankingcompose.core.ui.theme.DefaultBtn
import com.example.mobilebankingcompose.core.ui.theme.DefaultBtnText


@Composable
fun Button(
    click: () -> Unit, @StringRes string: Int,
    modifier: Modifier = Modifier,
    isEnable: Boolean = true,
    isLoading: Boolean = false
) {
    androidx.compose.material3.Button(
        { click.invoke() },
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEnable) Cerulean else DefaultBtn,
            contentColor = if (isEnable) Cerulean else DefaultBtn
        ),
    ) {
        if (!isLoading) {
            Text(
                text = stringResource(string),
                style = ComponentTextStyle.ButtonTextStyle,
                color = if (isEnable) Color.White else DefaultBtnText
            )
        } else {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier.size(17.dp),
                strokeWidth = 2.dp
            )
        }
    }
}