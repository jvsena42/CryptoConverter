package com.bulletapps.cryptoconverter.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bulletapps.cryptoconverter.domain.usecase.GetCryptoValuesUseCase


class MainActivityViewModelFactory(
        private val app: Application,
        private val getCryptoValuesUseCase: GetCryptoValuesUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(
            app,
            getCryptoValuesUseCase
        ) as T
    }
}