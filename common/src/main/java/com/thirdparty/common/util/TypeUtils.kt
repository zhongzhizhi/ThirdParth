package com.thirdparty.common.util

import com.google.gson.reflect.TypeToken


/**
 *  @项目名：  HappyChat
 *  @包名：    com.thirdparty.common.util
 *  @文件名:   TypeUtils
 *  @创建者:   Administrator
 *  @创建时间:  2021/10/20 17:09
 *  @描述：    TODO
 */
object TypeUtils {
    inline fun <reified T> genericType() = object: TypeToken<T>(){}.type
}