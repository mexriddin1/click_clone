package com.example.mobilebankingcompose.screen.home.transfer.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilebankingcompose.R
import com.example.mobilebankingcompose.core.ui.theme.Cerulean
import com.example.mobilebankingcompose.core.ui.theme.ComponentMargin
import com.example.mobilebankingcompose.core.ui.theme.ComponentRadius
import com.example.mobilebankingcompose.core.ui.theme.ComponentTextStyle
import com.example.mobilebankingcompose.core.ui.theme.DefaultBtn
import com.example.mobilebankingcompose.core.ui.theme.DefaultBtnText
import com.example.mobilebankingcompose.core.ui.theme.InputBackground
import com.example.mobilebankingcompose.core.ui.theme.InputBorder
import com.example.mobilebankingcompose.core.ui.theme.TextGray

@Composable
fun TransferInput(
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Done
    ),
    string: String = "",
    errorMessage: String? = null,
    onTextChange: (String) -> Unit,
    hintText: String = "",
    click: () -> Unit,
    isLoading: Boolean = false
) {
    var text by remember { mutableStateOf(string) }
    var hasFocus by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onTextChange.invoke(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = if (errorMessage.isNullOrBlank()) {
                        if (hasFocus) Cerulean else InputBorder
                    } else MaterialTheme.colorScheme.error,
                    shape = ComponentRadius.medium
                )
                .background(InputBackground, ComponentRadius.medium)
                .onFocusChanged { focusState -> hasFocus = focusState.hasFocus }
                .height(50.dp),
            singleLine = true,
            textStyle = ComponentTextStyle.InputTextStyle,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            cursorBrush = SolidColor(Cerulean),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 6.dp, bottom = 6.dp, start = 12.dp, end = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        innerTextField()

                        Button({
                            if (text.isNotBlank()){
                                click.invoke()
                            }
                        }, R.string.sent, modifier = Modifier.align(Alignment.CenterEnd), isEnable = text.isNotBlank(), isLoading = isLoading)

                        if (text.isEmpty()) {
                            Text(
                                modifier = Modifier
                                    .align(Alignment.CenterStart),
                                text = hintText,
                                style = TextStyle(
                                    color = TextGray,
                                    fontFamily = FontFamily(Font(R.font.euclid_circular_regular)),
                                    fontSize = 16.sp
                                )
                            )
                        }
                    }
                }

            }

        )


        if (!errorMessage.isNullOrBlank()) {
            Text(
                text = errorMessage ?: "",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(ComponentMargin.ErrorPadding)
            )
        }
    }
}

@Composable
fun Button(
    click: () -> Unit,
    @StringRes string: Int,
    modifier: Modifier = Modifier,
    isEnable: Boolean = true,
    isLoading: Boolean = false
) {
    Button(
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