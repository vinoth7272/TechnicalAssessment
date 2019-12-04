package com.example.technicalassessment.utils

import android.content.Context
import android.net.ConnectivityManager
import com.example.technicalassessment.model.FactsRows

class AppUtils {
    companion object {
        private var instance: AppUtils = AppUtils()

        @Synchronized
        fun getInstance(): AppUtils {
            return instance
        }
    }


    //To Check the internet connection
    fun isConnectedToNetwork(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
    }

    /**
     * used to filter the empty list item
     * @param rows list to filter
     */
    fun filterList(rows: List<FactsRows>): List<FactsRows> {
        val tempList: ArrayList<FactsRows> = ArrayList()
        for (row in rows) {
            if ((row.title != null && !row.title.isEmpty()) || (row.description != null && !row.description.isEmpty()))
                tempList.add(row)
        }
        return tempList
    }
}