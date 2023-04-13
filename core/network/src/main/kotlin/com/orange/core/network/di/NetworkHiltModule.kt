package com.orange.core.network.di

import com.orange.core.network.data_source.JsonFactory
import com.orange.core.network.data_source.NetworkApi
import com.orange.core.network.data_source.OkHttpClientFactory
import com.orange.core.network.data_source.RetrofitFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkHiltModule {
    @Singleton
    @Provides
    internal fun providesJson(): Json = JsonFactory.create()

    @Singleton
    @Provides
    internal fun providesOkHttpClient(): OkHttpClient =
        OkHttpClientFactory.create()

    @Singleton
    @Provides
    internal fun providesRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit =
        RetrofitFactory.create("https://api.ocs.fr/", okHttpClient, json)


    @Singleton
    @Provides
    internal fun providesNetworkApi(retrofit: Retrofit): NetworkApi =
        retrofit.create(NetworkApi::class.java)
}
