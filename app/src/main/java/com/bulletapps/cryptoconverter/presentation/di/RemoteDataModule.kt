package com.bulletapps.cryptoconverter.presentation.di

import com.bulletapps.cryptoconverter.BuildConfig
import com.bulletapps.cryptoconverter.data.api.GeckoAPIService
import com.bulletapps.cryptoconverter.data.repository.datasource.RemoteDataSource
import com.bulletapps.cryptoconverter.data.repository.datasourceimpl.RemoteDataSourceImpl
import com.bulletapps.cryptoconverter.presentation.adapter.CryptoAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(geckoAPIService: GeckoAPIService):RemoteDataSource{
        return RemoteDataSourceImpl(geckoAPIService)
    }
}