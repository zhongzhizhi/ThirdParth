package com.thirdparty.common.network

import com.thirdparty.common.network.api.ApiService


/**
 *  @项目名：  HappyChat
 *  @包名：    com.thirdparty.common.network
 *  @文件名:   CommonNetwork
 *  @创建者:   Administrator
 *  @创建时间:  2021/10/20 15:32
 *  @描述：    TODO
 */
class CommonNetwork private constructor() : BaseNetWork() {

    companion object {
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { CommonNetwork() }
    }

    private val apiService = ServiceCreator.create(ApiService::class.java)
}