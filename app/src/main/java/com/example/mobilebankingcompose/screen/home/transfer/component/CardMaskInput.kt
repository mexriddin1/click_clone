package com.example.mobilebankingcompose.screen.home.transfer.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilebankingcompose.R
import com.example.mobilebankingcompose.core.ui.theme.Cerulean
import com.example.mobilebankingcompose.core.ui.theme.ComponentPadding
import com.example.mobilebankingcompose.core.ui.theme.ComponentRadius
import com.example.mobilebankingcompose.core.ui.theme.ComponentSize
import com.example.mobilebankingcompose.core.ui.theme.ComponentTextStyle
import com.example.mobilebankingcompose.core.ui.theme.ErrorColor
import com.example.mobilebankingcompose.core.ui.theme.InputBackground
import com.example.mobilebankingcompose.core.ui.theme.InputBorder
import com.example.mobilebankingcompose.core.ui.theme.TextGray

@Composable
fun CardMaskInputForSearch(
    visualTransformation: VisualTransformation = CardMaskVisualTransformation(),
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Next
    ),
    string: String = "",
    errorMessage: Boolean = true,
    onTextChange: (String) -> Unit,
    hintText: String = "",
    borderSize: Dp = 0.7.dp,
    hintSize: Float = 16f
) {
    var text by remember { mutableStateOf(string) }
    var hasFocus by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box {
            BasicTextField(
                value = text,
                onValueChange = {
                    if (it.length <= 16) text = it
                    onTextChange.invoke(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = borderSize,
                        color = if (errorMessage) {
                            if (hasFocus) Cerulean else InputBorder
                        } else ErrorColor,
                        shape = ComponentRadius.medium
                    )
                    .background(InputBackground, ComponentRadius.medium)
                    .onFocusChanged { focusState -> hasFocus = focusState.hasFocus }
                    .height(ComponentSize.AuthConstantHeight),
                singleLine = true,
                textStyle = ComponentTextStyle.InputTextStyle,
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
                            innerTextField()
                            Icon(
                                painter = painterResource(if (text.isEmpty()) R.drawable.scan else R.drawable.cancel),
                                contentDescription = null,
                                tint = if (text.isEmpty()) Cerulean else TextGray,
                                modifier = Modifier
                                    .align(
                                        Alignment.CenterEnd
                                    )
                                    .size(22.dp)
                                    .clickable(
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() }
                                    ) {
                                        if (text.isNotBlank()) text = ""
                                    }

                            )
                        }
                    }
                },

                )

            if (text.isEmpty()) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 12.dp),
                    text = hintText,
                    style = TextStyle(
                        color = TextGray,
                        fontFamily = FontFamily(Font(R.font.euclid_circular_regular)),
                        fontSize = hintSize.sp
                    )
                )
            }
        }
    }
}

class CardMaskVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val rawText = text.text.filter { it.isDigit() }

        val maskedText = buildString {
            rawText.chunked(4).forEachIndexed { index, chunk ->
                if (index > 0) append(" ")
                append(chunk)
            }
        }

        return TransformedText(
            AnnotatedString(maskedText),
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {

                    val spaceCountBefore = (offset / 4)
                    return (offset + spaceCountBefore).coerceIn(0, maskedText.length)
                }

                override fun transformedToOriginal(offset: Int): Int {
                    val spaceCountBefore = (offset / 5)
                    return (offset - spaceCountBefore).coerceIn(0, rawText.length)
                }
            }
        )
    }
}