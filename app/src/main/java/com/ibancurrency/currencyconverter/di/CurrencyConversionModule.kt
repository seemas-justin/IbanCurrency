package com.ibancurrency.currencyconverter.di

import com.ibancurrency.currencyconverter.data.api.CurrencyConversionServices
import com.ibancurrency.currencyconverter.data.CurrencyConverterRepositoryImpl
import com.ibancurrency.currencyconverter.domain.CurrencyConverterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CurrencyConversionModule {

    @Singleton
    @Provides
    fun providesCurrencyConversionRepository(currencyConversionServices: CurrencyConversionServices) : CurrencyConverterRepository {
        return CurrencyConverterRepositoryImpl(currencyConversionServices)
    }

    @Singleton
    @Provides
    fun providesCurrencyConversionService(retrofit: Retrofit): CurrencyConversionServices {
        return retrofit.create<CurrencyConversionServices>(CurrencyConversionServices::class.java)
    }

}