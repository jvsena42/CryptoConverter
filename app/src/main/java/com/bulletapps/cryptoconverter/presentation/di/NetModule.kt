package com.bulletapps.cryptoconverter.presentation.di

import com.bulletapps.cryptoconverter.BuildConfig
import com.bulletapps.cryptoconverter.data.api.GeckoAPIService
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
class NetModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .build()
    }

    @Singleton
    @Provides
    fun providesGeckoAPIService(retrofit: Retrofit):GeckoAPIService{
        return retrofit.create(GeckoAPIService::class.java)
    }
}