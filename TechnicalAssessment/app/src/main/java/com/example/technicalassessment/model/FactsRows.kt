package com.example.technicalassessment.model

import com.google.gson.annotations.SerializedName

data class FactsRows(
    @SerializedName("title") var title: String,
    @SerializedName("description") var description: String,
    @SerializedName("imageHref") var imageHref: String
)
