package com.example.mobilebankingcompose.screen.auth.verify_code.constant

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.mobilebankingcompose.core.ui.theme.Cerulean
import com.example.mobilebankingcompose.core.ui.theme.ComponentMargin
import com.example.mobilebankingcompose.core.ui.theme.ComponentPadding
import com.example.mobilebankingcompose.core.ui.theme.ComponentRadius
import com.example.mobilebankingcompose.core.ui.theme.ComponentSize
import com.example.mobilebankingcompose.core.ui.theme.ComponentTextStyle
import com.example.mobilebankingcompose.core.ui.theme.InputBackground
import com.example.mobilebankingcompose.core.ui.theme.InputBorder


@Composable
fun InputCode(
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Done
    ),
    errorMessage: String? = null,
    onTextChange: (String) -> Unit,
    finishTimer: () -> Unit,
) {
    var code by remember { mutableStateOf("") }
    var hasFocus by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        BasicTextField(
            value = code,
            onValueChange = {
                if (it.length <= 6) {
                    code = it
                    onTextChange.invoke(it)
                }
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
                .height(ComponentSize.AuthConstantHeight),
            singleLine = true,
            textStyle = ComponentTextStyle.InputCodeStyle,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            cursorBrush = SolidColor(Cerulean),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(ComponentPadding.InputPadding),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {

                        Surface(
                            color = Color.Transparent,
                            modifier = Modifier.padding(start = 10.dp)
                        ) {
                            innerTextField()
                        }

                        Chronometer {
                            finishTimer.invoke()
                        }
                    }
                }
            }
        )
    }
    if (!errorMessage.isNullOrBlank()) {
        Text(
            text = errorMessage ?: "",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(ComponentMargin.ErrorPadding)
        )
    }
}
