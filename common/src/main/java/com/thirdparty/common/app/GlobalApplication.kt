package com.thirdparty.common.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ProcessLifecycleOwner
import com.thirdparty.common.basic.CommonApp
import com.thirdparty.common.observer.ProcessObserver
import com.thirdparty.common.util.AppUtil
import com.thirdparty.common.vervice.MessageService


/**
 *  @项目名：  HappyChat
 *  @包名：    com.thirdparty.common.app
 *  @文件名:   GlobalApplication
 *  @创建者:   Administrator
 *  @创建时间:  2021/10/20 14:18
 *  @描述：    TODO
 */
class GlobalApplication : Application(){


    companion object {
        private lateinit var context: Application

        @JvmStatic
        fun getContext(): Context{
            return context
        }
    }



    override fun onCreate() {
        super.onCreate()
        context = this

        CommonApp.init(this)
        AppUtil.init(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(ProcessObserver())

        startService(Intent(context, MessageService::class.java))
    }
}