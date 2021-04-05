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

    fun createList(apiResult: APIResponse?): MutableList<CryptoModel> {
        val listValues: MutableList<CryptoModel> = mutableListOf()
        listValues.clear()
        listValues.add(CryptoModel("BTC", apiResult?.bitcoin?.ars, apiResult?.bitcoin?.brl, apiResult?.bitcoin?.eur, apiResult?.bitcoin?.mxn, apiResult?.bitcoin?.usd, apiResult?.bitcoin?.lastUpdatedAt))
        listValues.add(CryptoModel("ETH", apiResult?.ethereum?.ars, apiResult?.ethereum?.brl, apiResult?.ethereum?.eur, apiResult?.ethereum?.mxn, apiResult?.ethereum?.usd, apiResult?.ethereum?.lastUpdatedAt))
        listValues.add(CryptoModel("LTC", apiResult?.litecoin?.ars, apiResult?.litecoin?.brl, apiResult?.litecoin?.eur, apiResult?.litecoin?.mxn, apiResult?.litecoin?.usd, apiResult?.litecoin?.lastUpdatedAt))
        listValues.add(CryptoModel("NANO", apiResult?.nano?.ars, apiResult?.nano?.brl, apiResult?.nano?.eur, apiResult?.nano?.mxn, apiResult?.nano?.usd, apiResult?.nano?.lastUpdatedAt))
        return listValues
    }
}