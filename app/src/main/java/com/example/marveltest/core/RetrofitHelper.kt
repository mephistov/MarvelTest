package com.example.marveltest.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com:443")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}