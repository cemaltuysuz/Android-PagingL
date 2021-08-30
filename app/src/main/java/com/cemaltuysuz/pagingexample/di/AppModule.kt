package com.cemaltuysuz.pagingexample.di

import com.cemaltuysuz.pagingexample.Constants
import com.cemaltuysuz.pagingexample.service.retrofit.Api
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    @Singleton
    fun injectRetrofitApi():Api{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.REMOTE.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttp())
            .build()
            .create(Api::class.java)
    }


    // OkHttp
    private fun okHttp() : OkHttpClient =
        OkHttpClient.Builder().apply {
            // logging and log level
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            this.addInterceptor(logging)
        }.build()
}