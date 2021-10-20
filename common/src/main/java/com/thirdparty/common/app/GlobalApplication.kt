package com.thirdparty.common.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context


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
        private lateinit var context: Context

        fun getApplicationContext(): Context{
            return context
        }
    }



    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}