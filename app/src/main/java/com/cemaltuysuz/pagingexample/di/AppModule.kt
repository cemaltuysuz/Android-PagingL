package com.cemaltuysuz.pagingexample.di

import android.content.Context
import androidx.room.Room
import com.cemaltuysuz.pagingexample.Constants
import com.cemaltuysuz.pagingexample.repo.UserRepo
import com.cemaltuysuz.pagingexample.repo.UserRepoInterface
import com.cemaltuysuz.pagingexample.service.retrofit.Api
import com.cemaltuysuz.pagingexample.service.room.UserDao
import com.cemaltuysuz.pagingexample.service.room.UserDatabase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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


    @Singleton
    @Provides
    fun injectDatabase(@ApplicationContext context:Context)  =
        Room.databaseBuilder(context,UserDatabase::class.java,"UserDB").build()

    @Singleton
    @Provides
    fun injectDao(database: UserDatabase) = database.userDao()

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



    @Singleton
    @Provides
    fun injectRepo(api:Api,dao:UserDao) = UserRepo(api,dao) as UserRepoInterface




    // OkHttp
    private fun okHttp() : OkHttpClient =
        OkHttpClient.Builder().apply {
            // logging and log level
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            this.addInterceptor(logging)
        }.build()
}