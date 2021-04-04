package com.bulletapps.cryptoconverter.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bulletapps.cryptoconverter.R
import com.bulletapps.cryptoconverter.data.model.APIResponse
import com.bulletapps.cryptoconverter.data.model.CryptoModel
import com.bulletapps.cryptoconverter.domain.usecase.GetCryptoValuesUseCase
import com.bulletapps.cryptoconverter.data.util.Resource
import com.bulletapps.cryptoconverter.data.util.isNetworkAvailable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivityViewModel(
        private val app: Application,
        private val getCryptoValuesUseCase: GetCryptoValuesUseCase
):AndroidViewModel(app) {
    val listCrypto:MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun getValues(ids:String,vsCurrencies:String) = viewModelScope.launch(Dispatchers.IO) {
        listCrypto.postValue(Resource.Loading())
        try {
            if(app.isNetworkAvailable()){
                val apiResult =getCryptoValuesUseCase.execute(ids,vsCurrencies)
                listCrypto.postValue(apiResult)

            }else{
                listCrypto.postValue(Resource.Error(app.getString(R.string.error_no_internet)))
            }
        }catch (e:Exception){
            listCrypto.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun createList(apiResult: Resource<APIResponse>): MutableList<CryptoModel> {
        val listValues: MutableList<CryptoModel> = mutableListOf()
        listValues.clear()
        listValues.add(CryptoModel("BTC", apiResult.data?.bitcoin?.ars, apiResult.data?.bitcoin?.brl, apiResult.data?.bitcoin?.eur, apiResult.data?.bitcoin?.mxn, apiResult.data?.bitcoin?.usd, apiResult.data?.bitcoin?.lastUpdatedAt))
        listValues.add(CryptoModel("ETH", apiResult.data?.ethereum?.ars, apiResult.data?.ethereum?.brl, apiResult.data?.ethereum?.eur, apiResult.data?.ethereum?.mxn, apiResult.data?.ethereum?.usd, apiResult.data?.ethereum?.lastUpdatedAt))
        listValues.add(CryptoModel("LTC", apiResult.data?.litecoin?.ars, apiResult.data?.litecoin?.brl, apiResult.data?.litecoin?.eur, apiResult.data?.litecoin?.mxn, apiResult.data?.litecoin?.usd, apiResult.data?.litecoin?.lastUpdatedAt))
        listValues.add(CryptoModel("NANO", apiResult.data?.nano?.ars, apiResult.data?.nano?.brl, apiResult.data?.nano?.eur, apiResult.data?.nano?.mxn, apiResult.data?.nano?.usd, apiResult.data?.nano?.lastUpdatedAt))
        return listValues
    }
}