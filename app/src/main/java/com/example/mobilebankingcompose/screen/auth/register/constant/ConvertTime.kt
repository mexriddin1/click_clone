package com.example.mobilebankingcompose.screen.auth.register.constant

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.abs

@RequiresApi(Build.VERSION_CODES.O)
fun convertDateToLong(dateString: String): String? {

    val list = dateString.split("/")

    val check = if (dateString.length == 10) {
        list[0].toInt() <= 31 && list[1].toInt() <= 12 && list[2].toInt() <= 2025
    } else false

    return if (check) {
        try {
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val date = formatter.parse(dateString)
            abs(date?.time ?: 0.toLong()).toString()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    } else null
}