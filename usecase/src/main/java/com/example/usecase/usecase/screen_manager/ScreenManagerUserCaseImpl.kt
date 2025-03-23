package com.example.usecase.usecase.screen_manager

import android.content.Context
import com.example.core.module.main.Language
import com.example.entity.local.AppLocalStorage
import com.example.entity.local.LocationHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class ScreenManagerUserCaseImpl @Inject constructor(
    private val localStorage: AppLocalStorage,
    @ApplicationContext val context: Context
) : ScreenManagerUserCase {
    override fun setNewPinCode(code: String): Flow<Result<Unit>> = flow {
        localStorage.pinCode = code
        emit(Result.success(Unit))
    }

    override fun checkPinCode(code: String): Flow<Result<Unit>> = flow {
        if (localStorage.pinCode == code) {
            emit(Result.success(Unit))
        } else {
            emit(Result.failure(Throwable("404")))
        }
    }

    override fun changeLanguage(language: Language) {
        LocationHelper.setLocation(context, language.country)
    }

}