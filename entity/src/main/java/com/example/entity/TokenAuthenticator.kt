package com.example.entity

import android.util.Log
import com.example.entity.local.AppLocalStorage
import com.example.entity.remote.auth.api.AuthApi
import com.example.entity.remote.auth.request.UpdateToken
import com.example.entity.remote.auth.response.UpdateTokenResponse
import dagger.Lazy
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
internal class TokenAuthenticator @Inject constructor(
    private val localStorage: AppLocalStorage,
    private val authApi: Lazy<AuthApi>,
) : Authenticator {
    private val header = "Authorization"

    override fun authenticate(route: Route?, response: Response): Request {
        synchronized(this) {
            val sessionData = if (isRefreshNeeded(response)) {
                runBlocking { getUpdatedSessionData() }
            } else {
                getExistingToken()
            }

            return response.request.newBuilder()
                .header(header, "Bearer ${sessionData.accessToken}")
                .build()
        }
    }

    private fun isRefreshNeeded(response: Response): Boolean {
        Log.d("TAG", "isRefreshNeeded: $response")

        return (response.code == 401)
    }

    private fun getExistingToken(): UpdateTokenResponse = UpdateTokenResponse(
        accessToken = localStorage.accessToken,
        refreshToken = localStorage.refreshToken
    )

    private suspend fun getUpdatedSessionData(): UpdateTokenResponse {
        val updateTokenRequest =
            authApi.get().updateToken(UpdateToken(refreshToken = localStorage.refreshToken))
        return updateTokenRequest.body()!!
    }
}