package com.chinky.family.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.chinky.family.R
import com.chinky.family.broadcast.AirplaneModeListener
import com.chinky.family.broadcast.AirplaneModeReceiver
import com.chinky.family.broadcast.ConnectivityChangeListener
import com.chinky.family.broadcast.ConnectivityChangeReceiver
import com.chinky.family.services.MusicService
import com.chinky.family.utility.Utility

class MainActivity : AppCompatActivity(), ConnectivityChangeListener, AirplaneModeListener {

    private val connectivityChangeReceiver: ConnectivityChangeReceiver =
        ConnectivityChangeReceiver(this)
    private val airplaneModeReceiver: AirplaneModeReceiver = AirplaneModeReceiver(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        Thread.currentThread().let { thread ->
            Utility.printLogCat(MainActivity::class.java, thread.getStackTrace()[2], "onCreate")
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.scrollMain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onResume() {
        super.onResume()
        val airplaneModeFilter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(airplaneModeReceiver, airplaneModeFilter)
        val connectivityChangeFilter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(connectivityChangeReceiver, connectivityChangeFilter)
        createNotificationChannel()
        startService(Intent(this, MusicService::class.java))
    }


    override fun onPause() {
        super.onPause()
        unregisterReceiver(airplaneModeReceiver)
        unregisterReceiver(connectivityChangeReceiver)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "music_channel",
                "Music Playback Channel",  // User-visible name
                NotificationManager.IMPORTANCE_HIGH // Importance level
            )
            channel.description = "This channel is used for music playback"

            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager?.createNotificationChannel(channel)
        }
    }

    override fun onConnectivityChangeBroadcastReceived(intent: Intent) {
        Thread.currentThread().let { thread ->
            Utility.printLogCat(MainActivity::class.java, thread.getStackTrace()[2], "onCreate")
        }
        Toast.makeText(this, "Connectivity Changed", Toast.LENGTH_SHORT).show()
    }

    override fun onAirplaneModeBroadcastReceived(intent: Intent) {
        Thread.currentThread().let { thread ->
            Utility.printLogCat(MainActivity::class.java, thread.getStackTrace()[2], "onCreate")
        }
        Toast.makeText(this, "Airplane Mode Changed", Toast.LENGTH_SHORT).show()
    }
}