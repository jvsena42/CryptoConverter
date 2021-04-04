package com.bulletapps.cryptoconverter.data.api

import com.bulletapps.cryptoconverter.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GeckoAPIService {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("ids")
        ids:String,
        @Query("vs_currencies")
        vsCurrencies:String,
        @Query("include_last_updated_at")
        lastUpdated:String = "true"
    ): Response<APIResponse>
}