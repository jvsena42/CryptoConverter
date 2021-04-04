package com.bulletapps.cryptoconverter.data.repository.datasource

import com.bulletapps.cryptoconverter.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.Query

interface RemoteDataSource {
    suspend fun getCryptoS(
        @Query("ids")
        ids:String,
        @Query("vs_currencies")
        vsCurrencies:String
    ):Response<APIResponse>
}