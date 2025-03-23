package com.example.mobilebankingcompose.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.core.screen.Screen
import com.example.mobilebankingcompose.core.utils.navigation.AppNavigator
import com.example.mobilebankingcompose.screen.auth.login.LoginScreen
import com.example.mobilebankingcompose.screen.pic_code.check_pin_code.EnterCodeScreen
import com.example.mobilebankingcompose.screen.pic_code.create_pin_code.CreateCodeScreen
import com.example.mobilebankingcompose.screen.select_language.SelectLanguageScreen
import com.example.preseneter.ScreenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val direction: AppNavigator,
    private val manager: ScreenManager
) : ViewModel() {
    init {
        viewModelScope.launch {
            delay(1500)
            direction.replace(if (manager.selectLanguage) {
                if (manager.register) {
                    if (manager.createPinCode) {
                        EnterCodeScreen()
                    } else CreateCodeScreen()
                } else LoginScreen()
            } else SelectLanguageScreen())
        }
    }
}