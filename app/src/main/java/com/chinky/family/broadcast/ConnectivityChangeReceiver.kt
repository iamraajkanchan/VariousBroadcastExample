package com.chinky.family.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ConnectivityChangeReceiver(private val listener: ConnectivityChangeListener) :
    BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let { i ->
            i.action?.let { a ->
                if (a == "android.net.conn.CONNECTIVITY_CHANGE") {
                    listener.onConnectivityChangeBroadcastReceived(i)
                }
            }
        }
    }
}