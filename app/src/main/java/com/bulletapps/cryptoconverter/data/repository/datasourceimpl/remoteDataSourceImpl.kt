package com.bulletapps.cryptoconverter.data.repository.datasourceimpl

import com.bulletapps.cryptoconverter.data.api.GeckoAPIService
import com.bulletapps.cryptoconverter.data.model.APIResponse
import com.bulletapps.cryptoconverter.data.repository.datasource.RemoteDataSource
import retrofit2.Response

class remoteDataSourceImpl(
    private val geckoAPIService: GeckoAPIService
):RemoteDataSource {
    override suspend fun getCryptoS(ids: String, vsCurrencies: String): Response<APIResponse> {
        return geckoAPIService.getCryptoValues(ids, vsCurrencies)
    }
}