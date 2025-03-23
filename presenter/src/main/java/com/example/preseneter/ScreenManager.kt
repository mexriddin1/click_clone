package com.example.preseneter

import android.content.Context
import com.example.core.extension.SharedPreference
import com.example.core.module.main.Language
import com.example.usecase.usecase.screen_manager.ScreenManagerUserCase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ScreenManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val screenManagerUserCase: ScreenManagerUserCase
) : SharedPreference(context) {

    fun setNewPinCode(code: String): Flow<Result<Unit>> = screenManagerUserCase.setNewPinCode(code)

    fun checkPinCode(code: String): Flow<Result<Unit>> = screenManagerUserCase.checkPinCode(code)

    fun changeLanguage(language: Language) {
        screenManagerUserCase.changeLanguage(language)
    }

    var register: Boolean by booleans(false)
    var selectLanguage: Boolean by booleans(false)
    var createPinCode: Boolean by booleans(false)


}