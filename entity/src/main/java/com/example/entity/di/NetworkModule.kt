package com.example.entity.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.entity.NetworkInterceptor
import com.example.entity.OfflineCacheInterceptor
import com.example.entity.TokenAuthenticator
import com.example.entity.remote.auth.api.AuthApi
import com.example.entity.remote.card.api.CardApi
import com.example.entity.remote.repors.api.ReportsApi
import com.example.entity.remote.transfer.api.TransferApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {

    private val baseUrl = "http://173.212.232.116/mobile-bank/"
    private val cacheSize = 10 * 1024 * 1024

    @[Provides Singleton]
    fun provideOkHttp(
        @ApplicationContext context: Context,
        tokenAuthenticator: TokenAuthenticator,
        offlineCacheInterceptor: OfflineCacheInterceptor,
        networkInterceptor:NetworkInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor(context))
        .cache(Cache(context.cacheDir, cacheSize.toLong()))
        .addNetworkInterceptor(networkInterceptor)
        .addInterceptor(offlineCacheInterceptor)
        .authenticator(tokenAuthenticator)
        .build()

    @[Provides Singleton]
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @[Provides Singleton]
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @[Provides Singleton]
    fun provideCardApi(retrofit: Retrofit): CardApi = retrofit.create(CardApi::class.java)

    @[Provides Singleton]
    fun provideTransferApi(retrofit: Retrofit): TransferApi =
        retrofit.create(TransferApi::class.java)

    @[Provides Singleton]
    fun provideReportsApi(retrofit: Retrofit): ReportsApi = retrofit.create(ReportsApi::class.java)
}


/*
.addInterceptor(Interceptor { chain ->
            var request = chain.request()
            request = if (hasNetwork(context)!!)
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
            else
                request.newBuilder().header(
                    "Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                ).build()

            chain.proceed(request)
        })

 */
