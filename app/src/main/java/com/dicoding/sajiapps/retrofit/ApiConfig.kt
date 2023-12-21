package com.dicoding.sajiapps.retrofit

import com.dicoding.sajiapps.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object{
        fun getApiService(token: String):ApiService{
            val logginInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logginInterceptor)
                .build()
            val baseUrl = com.dicoding.sajiapps.BuildConfig.BASE_URL
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
        fun getApiServicePredict():ApiServicePredict{
            val logginInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logginInterceptor)
                .build()
            val baseUrlPredict = BuildConfig.BASE_URL_PREDICT
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrlPredict)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiServicePredict::class.java)
        }
    }
}