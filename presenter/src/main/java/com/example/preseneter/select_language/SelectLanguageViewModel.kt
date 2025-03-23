package com.example.preseneter.select_language

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.module.main.Language
import com.example.preseneter.ScreenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectLanguageViewModel @Inject constructor(
    private val direction: SelectLanguageContract.Direction,
    private val manager: ScreenManager
) : ViewModel(), SelectLanguageContract.ViewModel {

    override var uiState = MutableStateFlow(SelectLanguageContract.UiState())
    override fun onAction(intent: SelectLanguageContract.Intent) {
        when (intent) {
            is SelectLanguageContract.Intent.ClickItem -> {
                viewModelScope.launch {
                    manager.changeLanguage(intent.language)
                    manager.selectLanguage = true
                    direction.openLogin()
                }
            }
        }
    }

    private inline fun reduce(block: (SelectLanguageContract.UiState) -> SelectLanguageContract.UiState) {
        val old = uiState.value
        val new = block(old)
        uiState.value = new
    }


    override suspend fun autoChangeLanguage(context: Context) {
        manager.changeLanguage(Language.UZB)
        reduce {
            uiState.value.copy(selectText = "0")
        }

        delay(3000)
        manager.changeLanguage(Language.RUS)
        reduce {
            uiState.value.copy(selectText = "1")
        }

        delay(3000)
        manager.changeLanguage(Language.ENG)
        reduce {
            uiState.value.copy(selectText = "2")
        }

        delay(3000)
        return autoChangeLanguage(context)
    }

}