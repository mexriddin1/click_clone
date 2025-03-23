package com.example.entity

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Cacheable

private fun cache(
    @ApplicationContext context: Context
): Cache {
    val cacheSize = 10 * 1024 * 1024.toLong()
    return Cache(context.cacheDir, cacheSize)
}

class NetworkInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val response = chain.proceed(chain.request())

        val cacheControl = CacheControl.Builder()
            .maxAge(5, TimeUnit.SECONDS)
            .build()

        return response.newBuilder()
            .removeHeader("HEADER_PRAGMA")
            .removeHeader("HEADER_CACHE_CONTROL")
            .header("HEADER_CACHE_CONTROL", cacheControl.toString())
            .build()
    }
}

open class OfflineCacheInterceptor @Inject constructor(@ApplicationContext val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val invocation: Invocation? = request.tag(Invocation::class.java)

        if (invocation != null) {
            val annotation: Cacheable? =
                invocation.method().getAnnotation(Cacheable::class.java)

            if (annotation != null &&
                annotation.annotationClass.simpleName.equals("Cacheable") &&
                !NetworkStatusValidator(context).isNetworkConnected()
            ) {

                val cacheControl = CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()

                request = request.newBuilder()
                    .removeHeader("HEADER_PRAGMA")
                    .removeHeader("HEADER_CACHE_CONTROL")
                    .cacheControl(cacheControl)
                    .build()
            }
        }
        return chain.proceed(request)
    }
}