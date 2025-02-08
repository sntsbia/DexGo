package com.sntsb.dexgo.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.MutableLiveData

class NetworkMonitor(context: Context) : ConnectivityManager.NetworkCallback() {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val _online = MutableLiveData<Boolean>()
    val online: MutableLiveData<Boolean> = _online

    fun addNetworkListener() {
        val networkRequest =
            NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()
        connectivityManager.registerNetworkCallback(networkRequest, this)
    }

    fun removeNetworkListener() {
        connectivityManager.unregisterNetworkCallback(this)
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        _online.postValue(true)

    }

    override fun onLost(network: Network) {
        super.onLost(network)

        _online.postValue(false)

    }
}