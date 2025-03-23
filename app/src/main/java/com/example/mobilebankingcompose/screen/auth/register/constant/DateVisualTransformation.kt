package com.example.mobilebankingcompose.screen.auth.register.constant

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class DateVisualTransformation : VisualTransformation {
    override fun filter(text:AnnotatedString): TransformedText {
        val inputText = text.text
        val formattedText = buildString {
            inputText.forEachIndexed { index, char ->
                if (index == 2 || index == 4) append("/")
                append(char)
            }
        }

        return TransformedText(
            text = AnnotatedString(formattedText),
            offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return when {
                        offset <= 2 -> offset
                        offset <= 4 -> offset + 1
                        else -> offset + 2
                    }
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return when {
                        offset <= 2 -> offset
                        offset <= 5 -> offset - 1
                        else -> offset - 2
                    }
                }
            }
        )
    }
}