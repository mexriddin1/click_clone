package com.example.entity.local

import android.content.Context
import com.example.core.extension.SharedPreference
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppLocalStorage @Inject constructor(@ApplicationContext context: Context) :
    SharedPreference(context) {
    var token: String by strings()
    var refreshToken: String by strings()
    var accessToken: String by strings()
    var pinCode: String by strings()
}