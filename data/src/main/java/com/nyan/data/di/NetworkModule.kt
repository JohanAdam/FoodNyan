package com.nyan.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nyan.data.service.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    fun provideHttpClient() : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesGson() : Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Singleton
    @Provides
    fun providesRetrofit(client: OkHttpClient, gson: Gson) : Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/JohanAdam/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
    }

    @Provides
    fun bindNetworkService(retrofit: Retrofit) : NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

}