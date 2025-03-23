package com.example.preseneter.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val direction: MainContract.Direction
) : ViewModel(), MainContract.ViewModel {

    override var uiState = MutableStateFlow(MainContract.UiState())

    override fun onAction(intent: MainContract.Intent) {
        when (intent) {
            is MainContract.Intent.OpenCardScreen -> {
                viewModelScope.launch { direction.openCard() }
            }
        }
    }
}
