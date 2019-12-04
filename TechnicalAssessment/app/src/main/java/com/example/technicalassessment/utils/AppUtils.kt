package com.example.technicalassessment.utils

import android.content.Context
import android.net.ConnectivityManager

class AppUtils  {
    companion object{

        //To Check the internet connection
        fun isConnectedToNetwork(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting() ?: false
        }
    }
}