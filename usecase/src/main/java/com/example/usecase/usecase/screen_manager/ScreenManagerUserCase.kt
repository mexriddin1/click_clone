package com.example.usecase.usecase.screen_manager

import com.example.core.module.main.Language
import kotlinx.coroutines.flow.Flow

interface ScreenManagerUserCase {
    fun setNewPinCode(code: String): Flow<Result<Unit>>

    fun checkPinCode(code: String): Flow<Result<Unit>>

    fun changeLanguage(language: Language)
}