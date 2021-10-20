package com.thirdparty.common.network.entity


/**
 *  @项目名：  meeting
 *  @包名：    com.dosmono.meetcommon.http.base
 *  @文件名:   DataRepo
 *  @创建者:   zer
 *  @创建时间:  2020/4/17 9:40
 *  @描述：    TODO
 */

open class BaseHttpResult(var code: Int = 0,var msg: String = "")

data class HttpResult<T>(val body: T) : BaseHttpResult()

