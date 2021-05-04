package com.nyan.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nyan.data.service.NetworkService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.sin

object NetworkModules {

    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    fun provideHttpClient() = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    fun providesGson() = GsonBuilder()
        .setLenient()
        .create()

    fun providesRetrofit(client: OkHttpClient, gson: Gson) = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/JohanAdam/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    fun providesNetworkService(retrofit: Retrofit) = retrofit.create(NetworkService::class.java)

    val networkModule = module {
        single { provideHttpClient() }
        single { providesGson() }
        single { providesRetrofit(get(), get()) }
        single { providesNetworkService(get()) }
    }

}
