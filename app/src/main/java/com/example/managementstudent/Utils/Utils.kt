package com.example.managementstudent.Utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import androidx.annotation.RequiresApi

object Utils {

    fun checkNetworkActive(context : Context) : Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager == null) return false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val network = connectivityManager.activeNetwork
            if (network == null) return false

            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            return networkCapabilities != null &&
                    (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN))
        }else {

            val networInfo = connectivityManager.activeNetworkInfo
            return networInfo != null && networInfo.isConnected
        }
    }
}