package com.example.mobilebankingcompose.core

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.example.mobilebankingcompose.core.ui.theme.DarkBackground
import com.example.mobilebankingcompose.core.utils.navigation.AppNavigatorHandler
import com.example.entity.local.LocationHelper
import com.example.mobilebankingcompose.screen.splash.SplashScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationHandler: AppNavigatorHandler

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DarkBackground)
            ) {
                Navigator(SplashScreen()) { navigator: Navigator ->
                    LaunchedEffect(key1 = navigator) {
                        navigationHandler.navigation
                            .collect {
                                it(navigator)
                            }
                    }
                    CurrentScreen()
                }
            }
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(com.example.entity.local.LocationHelper.attach(newBase))
    }
}