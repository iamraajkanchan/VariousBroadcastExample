package com.chinky.family.broadcast

import android.content.Intent

interface AirplaneModeListener {
    fun onAirplaneModeBroadcastReceived(intent: Intent)
}