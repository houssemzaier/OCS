package com.orange.core.network.data_source

import com.orange.core.network.models.ApiDetailsResponse
import com.orange.core.network.models.ApiSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface NetworkApi {
    @GET("apps/v2/contents")
    suspend fun searchContents(
        @Query("search") search: String,
    ): ApiSearchResponse


    @GET("apps/v2/details/programme/{path}")
    suspend fun getProgramDetails(@Path("path", encoded = true) detailsPath: String): ApiDetailsResponse
}
