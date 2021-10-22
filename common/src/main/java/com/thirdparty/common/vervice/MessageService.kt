package com.thirdparty.common.vervice

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.thirdparty.common.channel.WSChannel


/**
 *  @项目名：  HappyChat
 *  @包名：    com.thirdparty.common.vervice
 *  @文件名:   MessageService
 *  @创建者:   Administrator
 *  @创建时间:  2021/10/22 11:44
 *  @描述：    TODO
 */
class MessageService : Service() {
    private var mChannel: WSChannel? = null

    override fun onBind(intent: Intent?): IBinder? {
        return MessageBinder()
    }

    inner class MessageBinder : Binder() {
        val service: MessageService
            get() = this@MessageService
    }

    override fun onCreate() {
        super.onCreate()
        startForeground(1, getForeNotification())
        initChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        stopForeground(true)
    }

    private fun getForeNotification(): Notification {
        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "lechat"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(channelId,"Meeting", NotificationManager.IMPORTANCE_DEFAULT)
            nm.createNotificationChannel(channel)
        }
        return NotificationCompat.Builder(this,channelId)
            .setContentTitle("乐聊")
            .setContentText("")
            .build()
    }

    private fun initChannel(){
        WSChannel.addCallback(mWsCallback)
    }

    private val mWsCallback = object : WSChannel.ICallback {
        override fun onConnected(channel: WSChannel?) {
            mChannel = channel
        }

        override fun onError(code: Int) {

        }

        override fun onResponse(reply: String) {

        }

    }
}