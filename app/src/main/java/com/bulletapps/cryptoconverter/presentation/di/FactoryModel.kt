package com.bulletapps.cryptoconverter.presentation.di

import android.app.Application
import com.bulletapps.cryptoconverter.domain.usecase.GetCryptoValuesUseCase
import com.bulletapps.cryptoconverter.presentation.viewmodel.MainActivityViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModel {

    @Singleton
    @Provides
    fun providesMainActivityViewModelFactory(
            app: Application,
            getCryptoValuesUseCase: GetCryptoValuesUseCase
    ):MainActivityViewModelFactory{
        return MainActivityViewModelFactory(app, getCryptoValuesUseCase)
    }
}