package com.example.technicalassessment.NetworkCall

import com.example.technicalassessment.model.Facts
import retrofit2.Call
import retrofit2.http.GET

interface NetworkAPI {

    @GET("s/2iodh4vg0eortkl/facts.js")
    fun getUserContent(): Call<Facts>
}