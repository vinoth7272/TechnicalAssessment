package com.example.technicalassessment

import android.arch.lifecycle.Observer
import com.example.technicalassessment.model.Facts
import com.example.technicalassessment.model.FactsRows
import com.example.technicalassessment.model.Output
import com.example.technicalassessment.repository.DataRepository
import com.example.technicalassessment.viewmodel.ActivityViewModel
import com.nhaarman.mockitokotlin2.capture
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class ActivityViewModelTest {


    private lateinit var mockFactData: Facts
    @Mock
    lateinit var dataRepository: DataRepository
    lateinit var activityViewModel: ActivityViewModel
    private lateinit var isOutputLoadingObserver: Observer<Output>


    @Captor
    private lateinit var onFactsResponseDataCaptor: ArgumentCaptor<OnFactsResponseData>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.activityViewModel = ActivityViewModel(dataRepository)

        setUpObserver()

        mockData()
    }

    private fun setUpObserver() {
        // Mocked Observer
        isOutputLoadingObserver = mock(Observer::class.java) as Observer<Output>

    }

    @Test
    fun getFactsResponse() {
        /*Mockito.`when`(dataRepository.getFactsData(capture(onFactsResponseDataCaptor))).thenReturn(null)

        this.activityViewModel.output.observeForever(isOutputLoadingObserver)


        verify(dataRepository, times(1)).getFactsData(capture(onFactsResponseDataCaptor))

        //Invoke
        this.activityViewModel.getFacts()

        //Assertion
        assertNotNull(activityViewModel.output.value)
        assertEquals(onFactsResponseDataCaptor.value, this.activityViewModel.output.value)
        assert(this.activityViewModel.output.value is Throwable)*/

        with(activityViewModel){
            getFacts()
            output.observeForever(isOutputLoadingObserver)
        }


        verify(dataRepository, times(1)).getFactsData(capture(onFactsResponseDataCaptor))
//        onFactsResponseDataCaptor.value.onSuccess(mockFactData)
        assertEquals(mockFactData,onFactsResponseDataCaptor.value.onSuccess(mockFactData))
    }

    private fun mockData(){
        val mockList:MutableList<FactsRows>  = mutableListOf()
        mockList.add(FactsRows("test1","Museo Nacional de Arqueología, Antropología e Historia del Perú",""))
        mockFactData = Facts("Canada",mockList)

    }
}