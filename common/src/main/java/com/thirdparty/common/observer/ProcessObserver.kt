package com.thirdparty.common.observer

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.dosmono.logger.Logger


/**
 *  @项目名：  MeetingApp
 *  @包名：    com.dosmono.common.observer
 *  @文件名:   ProcessObserver
 *  @创建者:   zer
 *  @创建时间:  2020/11/17 9:06
 *  @描述：    TODO
 */
class ProcessObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart(){
        Logger.i("[Info] process onStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){
        Logger.i("[Info] process onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(){
        Logger.i("[Info] process onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop(){
        Logger.i("[Info] process onStop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(){
        Logger.i("[Info] process onDestroy")
    }
}