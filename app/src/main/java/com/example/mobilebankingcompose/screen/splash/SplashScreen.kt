package com.example.mobilebankingcompose.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.example.mobilebankingcompose.R
import com.example.mobilebankingcompose.core.ui.theme.Cerulean

class SplashScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<SplashViewModel>()
        SplashScreenContent()
    }
}

@Composable
private fun SplashScreenContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Cerulean)
    ) {
        Image(
            painter = painterResource(R.drawable.click_icon),
            contentDescription = null,
            modifier = Modifier
                .size(59.dp)
                .align(Alignment.Center)
        )
    }

}