package com.orange.core.network.data_source

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

internal object OkHttpClientFactory {

    private const val OK_HTTP_CONNECT_TIMEOUT = 60L
    private const val OK_HTTP_WRITE_TIMEOUT = 60L
    private const val OK_HTTP_READ_TIMEOUT = 60L

    private val loggingInterceptor =
        HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }

    private val debuggingInterceptor = StethoInterceptor()

    fun create(): OkHttpClient {

        return OkHttpClient.Builder()
            .connectTimeout(OK_HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(OK_HTTP_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(OK_HTTP_READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addNetworkInterceptor(debuggingInterceptor)
            .build()
    }
}
