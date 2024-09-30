package com.chinbri.indexacapitalapp.di

import com.chinbri.indexacapitalapp.data.datasource.network.IndexaNetworkDataSource
import com.chinbri.indexacapitalapp.data.datasource.network.IndexaNetworkDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DataSourcesModule {

    //provide IndexaNetworkDataSource
    @Provides
    fun provideIndexaNetworkDataSource(impl: IndexaNetworkDataSourceImpl): IndexaNetworkDataSource =
        impl

}