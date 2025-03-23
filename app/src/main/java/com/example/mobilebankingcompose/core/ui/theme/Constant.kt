package com.example.mobilebankingcompose.core.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilebankingcompose.R

object ComponentSize {
    val AuthConstantHeight: Dp = 45.dp
}

object ComponentMargin {
    val HorizontalPadding: PaddingValues = PaddingValues(horizontal = 15.dp)
    val TextPadding: PaddingValues = PaddingValues(top = 20.dp, bottom = 10.dp)
    val ErrorPadding: PaddingValues = PaddingValues(top = 2.dp)
}

object ComponentPadding {
    val InputPadding: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
}

object ComponentRadius {
    val none = RoundedCornerShape(0.dp)
    val small = RoundedCornerShape(4.dp)
    val medium = RoundedCornerShape(8.dp)
    val regular = RoundedCornerShape(12.dp)
    val bold = RoundedCornerShape(18.dp)
}

object ComponentTextStyle {
    val InputTextStyle = TextStyle(
        color = Color.White,
        fontFamily = FontFamily(Font(R.font.euclid_circular_regular)),
        fontSize = 16.sp
    )

    val InputCodeStyle = TextStyle(
        color = Color.White,
        fontFamily = FontFamily(Font(R.font.euclid_circular_bold)),
        fontSize = 20.sp,
        letterSpacing = 2.sp
    )

    val CodeTextBlueStyle = TextStyle(
        color = Cerulean,
        fontFamily = FontFamily(Font(R.font.euclid_circular_bold)),
        fontSize = 30.sp,
        letterSpacing = 10.sp
    )

    val CodeTextStyle = TextStyle(
        color = Color.White,
        fontFamily = FontFamily(Font(R.font.euclid_circular_bold)),
        fontSize = 30.sp,
        letterSpacing = 10.sp
    )

    val InputTitle = TextStyle(
        color = Color.White,
        fontFamily = FontFamily(Font(R.font.sf_ui_display_bold)),
        fontSize = 16.sp,
        letterSpacing = TextUnit(0.03f, TextUnitType(1))
    )

    val ButtonTextStyle = TextStyle(
        color = Color.White,
        fontFamily = FontFamily(Font(R.font.circe_rounded_regular_bold)),
        fontSize = 15.sp
    )

    val ResendTextStyle = TextStyle(
        color = Cerulean,
        fontFamily = FontFamily(Font(R.font.circe_rounded_regular)),
        fontSize = 16.sp
    )

    val TopAppBarText = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.euclid_circular_medium)
        ), fontSize = 16.sp, color = Color.White
    )

    val TopAppBarCodeText = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.euclid_circular_bold)
        ), fontSize = 20.sp, color = Color.White
    )

    val PinCodeText = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.euclid_circular_medium)
        ), fontSize = 18.sp, color = Color.White
    )

    val PinCodeGreyText = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.euclid_circular_regular)
        ), fontSize = 17.sp, color = TextGray
    )
}

