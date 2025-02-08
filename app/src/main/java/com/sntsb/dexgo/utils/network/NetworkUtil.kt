package com.sntsb.dexgo.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object NetworkUtil {

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkNetworkAvailabilityPostMarshmallow(connectivityManager)
        } else {
            checkNetworkAvailabilityPreMarshmallow(connectivityManager)
        }
    }

    private fun checkNetworkAvailabilityPostMarshmallow(connectivityManager: ConnectivityManager): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) && (activeNetwork.hasTransport(
            NetworkCapabilities.TRANSPORT_WIFI
        ) || activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || activeNetwork.hasTransport(
            NetworkCapabilities.TRANSPORT_ETHERNET
        ) || activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_VPN))
    }


    private fun checkNetworkAvailabilityPreMarshmallow(connectivityManager: ConnectivityManager): Boolean {
        val networkInfo = connectivityManager.activeNetworkInfo ?: return false
        return networkInfo.isConnected
    }
}