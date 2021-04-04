package com.bulletapps.cryptoconverter.data

import com.bulletapps.cryptoconverter.data.model.APIResponse
import com.bulletapps.cryptoconverter.data.repository.datasource.RemoteDataSource
import com.bulletapps.cryptoconverter.domain.repository.CryptoRepository
import com.bulletapps.cryptoconverter.data.util.Resource
import retrofit2.Response

class CryptoRepositoryImpl(private val remoteDataSource: RemoteDataSource):CryptoRepository {
    override suspend fun getCryptoValues(ids: String, vsCurrencies: String): Resource<APIResponse> {
        return responseToResource(remoteDataSource.getCryptoS(ids, vsCurrencies))
    }

    private fun responseToResource(response: Response<APIResponse>): Resource<APIResponse> {
        if (response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}