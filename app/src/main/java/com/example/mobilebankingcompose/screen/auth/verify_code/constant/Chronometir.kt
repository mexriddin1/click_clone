package com.example.mobilebankingcompose.screen.auth.verify_code.constant

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.mobilebankingcompose.core.ui.theme.ComponentTextStyle

@SuppressLint("DefaultLocale")
@Composable
fun BoxScope.Chronometer(onFinished: () -> Unit) {
    val initialTime = 60
    var remainingTime by remember { mutableIntStateOf(initialTime) }
    var isFinished by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = remainingTime) {
        if (remainingTime > 0) {
            kotlinx.coroutines.delay(1000L)
            remainingTime--
        } else {
            isFinished = true
            onFinished()
        }
    }

    if (!isFinished) {
        val minutes = remainingTime / 60
        val seconds = remainingTime % 60

        Text(
            text = String.format("%02d:%02d", minutes, seconds),
            style = ComponentTextStyle.TopAppBarText,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    } else {
        Text(
            text = "00:00",
            style = ComponentTextStyle.TopAppBarText,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}