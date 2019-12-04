package com.example.technicalassessment.model

import com.google.gson.annotations.SerializedName

data class Facts(
    @SerializedName("title") var title: String,
    @SerializedName("rows") var rows: List<FactsRows>
)
