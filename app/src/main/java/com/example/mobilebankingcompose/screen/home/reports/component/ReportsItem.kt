package com.example.mobilebankingcompose.screen.home.reports.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilebankingcompose.R
import com.example.mobilebankingcompose.core.ui.theme.TextGray
import com.example.core.module.transfer.ReportsData
import com.example.mobilebankingcompose.screen.home.main.component.MainSumText
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ReportsItem(
    data: com.example.core.module.transfer.ReportsData
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.history_icon),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 10.dp)
                .size(40.dp)
        )
        Text(
            text = data.name, style = TextStyle(
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.euclid_circular_medium))
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        Column {
            MainSumText(text = data.sum, state = true, textSize = 16, textSum = 13, padding = 1)
            Text(
                text = formatLongTimeToCustomString(data.time),
                style = TextStyle(
                    color = TextGray,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.euclid_circular_medium))
                )
            )
        }
    }
}

fun formatLongTimeToCustomString(timeInMillis: Long): String {
    val date = Date(timeInMillis)
    val dateFormat = SimpleDateFormat("dd HH:mm", Locale("uz"))
    val formattedDate = dateFormat.format(date)
    return "${formattedDate.substring(0, 2)} ${getShortMonthName(date)} ${formattedDate.substring(3)}"
}

fun getShortMonthName(date: Date): String {
    val shortMonthNames = listOf(
        "yan", "fev", "mar", "apr", "may", "iyn",
        "iyl", "avg", "sen", "okt", "noy", "dek"
    )
    val calendar = java.util.Calendar.getInstance()
    calendar.time = date
    return shortMonthNames[calendar.get(java.util.Calendar.MONTH)]
}
