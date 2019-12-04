package com.example.technicalassessment.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.technicalassessment.OnFactsResponseData
import com.example.technicalassessment.model.Facts
import com.example.technicalassessment.model.Output
import com.example.technicalassessment.repository.DataRepository

class ActivityViewModel(private var dataRepository: DataRepository) : ViewModel() {
    var output: MutableLiveData<Output> = MutableLiveData()

    // used to call the dataRepository Api Method
    fun getFacts() {

        dataRepository.getFactsData(object : OnFactsResponseData {
            override fun onSuccess(data: Facts) {
                output.value = Output.Success(data)
            }

            override fun onFailure(error: String) {
                output.value = Output.Error(error)
            }
        })

    }


}