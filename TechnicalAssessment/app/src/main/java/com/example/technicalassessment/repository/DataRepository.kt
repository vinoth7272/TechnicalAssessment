package com.example.technicalassessment.repository

import com.example.technicalassessment.NetworkCall.NetworkClient
import com.example.technicalassessment.model.Facts
import retrofit2.Call
import retrofit2.Response

class DataRepository {

    /**
     * Method to call the API and fetch data using Retrofit
     * @param factsResponseData instance of interface object
     */
    fun getFactsData(factsResponseData: OnFactsResponseData) {
        val call: Call<Facts> = NetworkClient.getRetrofitClient().getUserContent()
        call.enqueue(object : retrofit2.Callback<Facts> {
            override fun onFailure(call: Call<Facts>, t: Throwable) {
                factsResponseData.onFailure()
            }

            override fun onResponse(call: Call<Facts>, response: Response<Facts>) {

                if (response.code() == 200) {
                    factsResponseData.onSuccess(response.body() as Facts)
                } else {
                    factsResponseData.onFailure()
                }
            }

        })
    }

    //Interface for call back to Viewmodel
    interface OnFactsResponseData {
        fun onSuccess(data: Facts)
        fun onFailure()
    }
}