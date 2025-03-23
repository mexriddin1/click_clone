package com.example.mobilebankingcompose.screen.home.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.mobilebankingcompose.R
import com.example.mobilebankingcompose.core.ui.theme.Cerulean
import com.example.mobilebankingcompose.core.ui.theme.ComponentPadding
import com.example.mobilebankingcompose.core.ui.theme.ComponentRadius
import com.example.mobilebankingcompose.core.ui.theme.ComponentSize
import com.example.mobilebankingcompose.core.ui.theme.ComponentTextStyle
import com.example.mobilebankingcompose.core.ui.theme.InputBackground
import com.example.mobilebankingcompose.core.ui.theme.InputBorder

@Composable
fun SearchBarColorsText(
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextChange: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }

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
                color = InputBorder,
                shape = ComponentRadius.medium
            )
            .background(InputBackground, ComponentRadius.medium)
            .height(ComponentSize.AuthConstantHeight),
        singleLine = true,
        textStyle = ComponentTextStyle.InputTextStyle,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text
        ),
        cursorBrush = SolidColor(Cerulean),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(ComponentPadding.InputPadding),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(painter = painterResource(R.drawable.search), contentDescription = null)

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {

                    innerTextField()
                }
            }
        }

    )

}