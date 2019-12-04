package com.example.technicalassessment

import com.example.technicalassessment.model.Facts
import com.example.technicalassessment.model.FactsRows
import com.example.technicalassessment.utils.AppUtils
import junit.framework.Assert.*
import org.junit.Test
import org.mockito.Mock

class AppUtilsTest {

    @Mock
    private val appUtils: AppUtils = AppUtils()

    @Test
    fun listFilterTest() {
        val filteredList: List<FactsRows> = appUtils.filterList(mockData())

        assertNotNull(filteredList)
        assertTrue("Test Size",filteredList.size == 2)
        assert(!filteredList.isEmpty())
    }


    private fun mockData(): List<FactsRows> {
        val mockList: MutableList<FactsRows> = mutableListOf()
        mockList.add(FactsRows("test1", "Museo Nacional de Arqueología, Antropología e Historia del Perú", ""))
        mockList.add(FactsRows("test2", "Museo Nacional de Arqueología, Antropología e Historia del Perú", ""))
        mockList.add(FactsRows("", "", ""))
        return mockList
    }
}