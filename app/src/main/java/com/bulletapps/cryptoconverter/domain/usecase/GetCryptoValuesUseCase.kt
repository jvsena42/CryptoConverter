package com.bulletapps.cryptoconverter.domain.usecase

import com.bulletapps.cryptoconverter.data.model.APIResponse
import com.bulletapps.cryptoconverter.domain.repository.CryptoRepository
import com.bulletapps.newsapp.data.util.Resource

class GetCryptoValuesUseCase(private val cryptoRepository: CryptoRepository) {
    suspend fun execute( ids:String,vsCurrencies:String):Resource<APIResponse>{
        return cryptoRepository.getCryptoValues(ids, vsCurrencies)
    }
}