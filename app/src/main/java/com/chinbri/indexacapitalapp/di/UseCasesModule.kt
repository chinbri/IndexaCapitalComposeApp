package com.chinbri.indexacapitalapp.di

import com.chinbri.indexacapitalapp.domain.usecase.GetAccountInfoUseCase
import com.chinbri.indexacapitalapp.domain.usecase.GetAccountInfoUseCaseImpl
import com.chinbri.indexacapitalapp.domain.usecase.GetUserInfoUseCase
import com.chinbri.indexacapitalapp.domain.usecase.GetUserInfoUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    @Provides
    fun provideUpdateLocalWorkOrderDetailUseCase(impl: GetUserInfoUseCaseImpl): GetUserInfoUseCase =
        impl

    @Provides
    fun provideGetAccountInfoUseCase(impl: GetAccountInfoUseCaseImpl): GetAccountInfoUseCase =
        impl

}

