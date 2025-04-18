package com.chinky.family.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.chinky.family.R


class MusicService : Service() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.background_music)
        mediaPlayer?.isLooping = true;
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(1, createNotification())
        mediaPlayer?.start()
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.let { player ->
            if (player.isPlaying) {
                player.stop()
            }
            player.release()
        }
    }

    private fun createNotification(): Notification {
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, "music_channel")
                .setChannelId("music_channel")
                .setSmallIcon(R.drawable.ic_music_play)
                .setContentTitle("Chinky Music Player")
                .setContentText("Playing Om Mangalam")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
        return builder.build();
    }
}