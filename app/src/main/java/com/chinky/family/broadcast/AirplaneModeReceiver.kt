package com.chinky.family.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AirplaneModeReceiver(private val listener: AirplaneModeListener) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let { i ->
            i.action?.let { a ->
                if (a == "android.intent.action.AIRPLANE_MODE") {
                    listener.onAirplaneModeBroadcastReceived(i)
                }
            }
        }
    }
}