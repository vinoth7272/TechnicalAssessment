package com.example.technicalassessment.model

sealed class Output{

    data class Success(val facts: Facts) : Output()
    data class Error(val error: String?) : Output()
}