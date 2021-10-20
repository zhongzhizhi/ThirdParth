package com.thirdparty.common.basic

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner


/**
 *  @项目名：  MeetingApp
 *  @包名：    com.dosmono.common.basic
 *  @文件名:   CommonApp
 *  @创建者:   zer
 *  @创建时间:  2020/10/10 9:00
 *  @描述：    TODO
 */
class CommonApp private constructor() : ViewModelStoreOwner{
    private var mFactory: ViewModelProvider.Factory? = null

    companion object{
        private lateinit var mAppViewModelStore: ViewModelStore
        private lateinit var mApplication: Application
        private lateinit var mInstance: CommonApp

        fun init(application: Application){
            mApplication = application
            mAppViewModelStore = ViewModelStore()
            mInstance = CommonApp()
        }

        fun getInstance(): CommonApp {
            if (!Companion::mInstance.isInitialized){
                @Synchronized
                if (!Companion::mInstance.isInitialized){
                    mInstance = CommonApp()
                }
            }
            return mInstance
        }
    }

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }

    fun getAppViewModelProvider(activity: Activity): ViewModelProvider{
        return ViewModelProvider(this,getAppFactory(activity))
    }

    private fun getAppFactory(activity: Activity): ViewModelProvider.Factory{
        val application = checkApplication(activity)
        if (mFactory == null){
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        }
        return mFactory!!
    }

    private fun checkApplication(activity: Activity): Application {
        return activity.application
            ?: throw IllegalStateException(
                "Your activity/fragment is not yet attached to "
                        + "Application. You can't request ViewModel before onCreate call."
            )
    }


}