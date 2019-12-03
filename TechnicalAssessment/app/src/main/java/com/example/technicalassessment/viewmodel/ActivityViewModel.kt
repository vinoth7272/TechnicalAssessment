package com.example.technicalassessment.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.technicalassessment.model.Facts
import com.example.technicalassessment.repository.DataRepository

class ActivityViewModel : ViewModel() {
    var facts: MutableLiveData<Facts> = MutableLiveData()
    var resultStatus: MutableLiveData<Boolean> = MutableLiveData()

    private val dataRepository: DataRepository = DataRepository()

    init {
        resultStatus.value = false
    }

    // used to call the dataRepository Api Method
    fun getFacts() {
        dataRepository.getFactsData(object : DataRepository.OnFactsResponseData {
            override fun onSuccess(data: Facts) {
                resultStatus.value = true
                facts.value = data
            }

            override fun onFailure() {
                resultStatus.value = false
            }
        })
    }
}