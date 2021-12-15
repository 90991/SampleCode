package com.example.sampe.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitModule {

    private val BASE_URL = "https://jsonplaceholder.typicode.com/"

    fun provideRetrofit(): DataService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DataService::class.java)
    }

}