package com.example.mobilebankingcompose.screen.auth.component

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneMaskVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val rawText = text.text.filter { it.isDigit() }
        val maskedText = buildString {
            append("+998 (")
            for (i in rawText.indices) {
                when (i) {
                    2 -> append(") ")
                    5 -> append("-")
                    7 -> append("-")
                }
                append(rawText[i])
            }
        }

        return TransformedText(
            AnnotatedString(maskedText),
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return when (offset) {
                        in 0..2 -> offset + 6
                        in 3..5 -> offset + 8
                        in 6..8 -> offset + 9
                        else -> maskedText.length
                    }
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return when {
                        offset <= 6 -> offset - 6
                        offset <= 9 -> offset - 8
                        offset <= 12 -> offset - 9
                        else -> rawText.length
                    }
                }
            }
        )
    }
}