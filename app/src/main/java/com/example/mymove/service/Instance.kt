package com.example.mymove.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Instance {
    private val URL : String = "https://api.themoviedb.org/"

    private val retrofit = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(Service::class.java)
}