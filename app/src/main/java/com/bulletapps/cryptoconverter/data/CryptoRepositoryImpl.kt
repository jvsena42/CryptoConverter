package com.bulletapps.cryptoconverter.data

import com.bulletapps.cryptoconverter.data.model.APIResponse
import com.bulletapps.cryptoconverter.data.model.ErrorModel
import com.bulletapps.cryptoconverter.data.repository.datasource.RemoteDataSource
import com.bulletapps.cryptoconverter.domain.repository.CryptoRepository
import com.bulletapps.cryptoconverter.data.util.Resource
import com.google.gson.Gson
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
        val message = Gson().fromJson(response.errorBody()?.string(), ErrorModel::class.java)
        return Resource.Error(message.error.toString())
    }
}