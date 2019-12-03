package com.example.technicalassessment.NetworkCall

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClient {
    companion object {

        val BASE_URL = "https://dl.dropboxusercontent.com/" // Base Url for the API
        // method to create retrofit object
        fun getRetrofitClient(): NetworkAPI {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(NetworkAPI::class.java)
        }
    }


}