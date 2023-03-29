package com.ibancurrency.ibavalidater.di

import com.google.gson.Gson
import com.ibancurrency.ibavalidater.data.IbanValidatorRepositoryImpl
import com.ibancurrency.ibavalidater.data.api.IbanValidationService
import com.ibancurrency.ibavalidater.domain.IbanValidatorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class IbanValidatorModule {
    @Singleton
    @Provides
    fun providesIbanRepository(ibanValidationService: IbanValidationService, gson: Gson): IbanValidatorRepository {
        return IbanValidatorRepositoryImpl(ibanValidationService, gson)
    }
    @Singleton
    @Provides
    fun providesIbanApiService(retrofit: Retrofit) : IbanValidationService {
        return retrofit.create(IbanValidationService::class.java)
    }
}