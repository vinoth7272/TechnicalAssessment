package com.example.technicalassessment

import com.example.technicalassessment.model.Facts

interface OnFactsResponseData {
    fun onSuccess(data: Facts)
    fun onFailure(error:String)
}