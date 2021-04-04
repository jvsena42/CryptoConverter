package com.bulletapps.cryptoconverter.domain.repository

import com.bulletapps.cryptoconverter.data.model.APIResponse
import com.bulletapps.cryptoconverter.data.util.Resource

interface CryptoRepository {
    suspend fun getCryptoValues(
        ids:String,
        vsCurrencies:String
    ): Resource<APIResponse>
}