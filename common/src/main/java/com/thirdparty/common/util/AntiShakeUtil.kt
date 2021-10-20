package com.thirdparty.common.util

import android.os.SystemClock


/**
 *  @项目名：  MeetingApp
 *  @包名：    com.dosmono.common.util
 *  @文件名:   AntiShakeUtil
 *  @创建者:   zer
 *  @创建时间:  2020/10/10 17:57
 *  @描述：    TODO
 */
object AntiShakeUtil {
    private var preTime: Long = 0L
    private var preResId = 0

    fun isShake(diff: Int = 500): Boolean{
        val time = SystemClock.uptimeMillis()
        val diffTime = time - preTime
        if (diffTime > diff){
            preTime = time
            return false
        }
        return true
    }

    fun isShakeWith(resId: Int,diff: Int = 500): Boolean{
        val time = SystemClock.uptimeMillis()
        val diffTime = time - preTime
        if (resId == preResId && preResId != 0 && diffTime < diff){
            return true
        }
        preTime = time
        preResId = resId
        return false
    }
}