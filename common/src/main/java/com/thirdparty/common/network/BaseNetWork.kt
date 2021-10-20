package com.thirdparty.common.network

import com.alibaba.fastjson.JSON
import com.thirdparty.common.network.entity.CommonReq
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


/**
 *  @项目名：  MeetingApp
 *  @包名：    com.dosmono.common.network
 *  @文件名:   BaseNetWork
 *  @创建者:   zer
 *  @创建时间:  2021/5/25 13:53
 *  @描述：    TODO
 */
open class BaseNetWork{

    protected fun <T> getRequestBody(body: T): RequestBody {
        val request = CommonReq<T>()
        request.body = body
        val params = JSON.toJSONString(request)
        return params.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
    }

    protected suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    response.body()?.apply {
                        continuation.resume(this)
                    } ?: continuation.resumeWithException(RuntimeException("response body is null"))
                }
            })
        }
    }
}