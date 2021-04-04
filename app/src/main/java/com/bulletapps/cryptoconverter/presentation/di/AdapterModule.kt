package com.bulletapps.cryptoconverter.presentation.di

import com.bulletapps.cryptoconverter.presentation.adapter.CryptoAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun providesCryptoAdapter():CryptoAdapter{
        return CryptoAdapter()
    }
}