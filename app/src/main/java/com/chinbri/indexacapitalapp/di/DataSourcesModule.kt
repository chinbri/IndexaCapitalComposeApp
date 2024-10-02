package com.chinbri.indexacapitalapp.di

import com.chinbri.indexacapitalapp.BuildConfig
import com.chinbri.indexacapitalapp.data.api.IndexaCapitalAppApi
import com.chinbri.indexacapitalapp.data.datasource.local.IndexaLocalDataSource
import com.chinbri.indexacapitalapp.data.datasource.local.IndexaLocalDataSourceImpl
import com.chinbri.indexacapitalapp.data.datasource.network.IndexaNetworkDataSource
import com.chinbri.indexacapitalapp.data.datasource.network.IndexaNetworkDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class DataSourcesModule {

    @Provides
    fun provideIndexaNetworkDataSource(impl: IndexaNetworkDataSourceImpl): IndexaNetworkDataSource =
        impl

    @Provides
    fun provideIndexaLocalDataSource(impl: IndexaLocalDataSourceImpl): IndexaLocalDataSource =
        impl

    //retrofit
    @Provides
    fun provideRetrofit(): IndexaCapitalAppApi {

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IndexaCapitalAppApi::class.java)
    }


}