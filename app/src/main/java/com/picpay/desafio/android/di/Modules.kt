package com.picpay.desafio.android.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.network.PicPayService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideGson() }
    factory { provideOkHttp() }
    single { provideRetrofit(get(), get()) }
    factory { providePicPayService(get()) }
}

private fun provideRetrofit(client: OkHttpClient, gsonBuilder: Gson) = Retrofit.Builder()
    .baseUrl(URL)
    .client(client)
    .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
    .build()


private fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder().build()

private fun provideGson() = GsonBuilder().create()

private fun providePicPayService(retrofit: Retrofit): PicPayService =
    retrofit.create(PicPayService::class.java)


private const val URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

