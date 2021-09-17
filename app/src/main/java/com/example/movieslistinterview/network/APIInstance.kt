package com.example.movieslistinterview.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIInstance {
    fun <T> buildService(service: Class<T>): T {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(interceptor)
        clientBuilder.connectTimeout(10, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(30, TimeUnit.SECONDS)
        clientBuilder.readTimeout(1, TimeUnit.MINUTES)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/?")
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuilder.build())
            .build()
        return retrofit.create(service)
    }
}