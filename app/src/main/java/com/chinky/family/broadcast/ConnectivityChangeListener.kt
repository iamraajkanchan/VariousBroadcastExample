package com.chinky.family.broadcast

import android.content.Intent

interface ConnectivityChangeListener {
    fun onConnectivityChangeBroadcastReceived(intent: Intent)
}