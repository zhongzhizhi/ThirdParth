package com.thirdparty.common.network

import android.content.Intent
import com.alibaba.fastjson.JSON
import com.thirdparty.common.BuildConfig
import com.thirdparty.common.config.CommonConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.URLDecoder
import java.util.concurrent.TimeUnit

object ServiceCreator {

    private fun logging(): Interceptor {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG){
            logging.level = HttpLoggingInterceptor.Level.BODY
        }else{
            logging.level = HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

    private fun getParam(requestBody: RequestBody): String? {
        val buffer = Buffer()
        var logparm: String
        try {
            requestBody.writeTo(buffer)
            logparm = buffer.readUtf8()
            logparm = URLDecoder.decode(logparm, "utf-8")
        } catch (e: IOException) {
            e.printStackTrace()
            return ""
        }
        return logparm
    }

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(6 * 1000, TimeUnit.MILLISECONDS)
        .readTimeout(6 * 1000, TimeUnit.MILLISECONDS)
        .writeTimeout(6 * 1000, TimeUnit.MILLISECONDS)
        .addNetworkInterceptor(logging())

    private val builder = Retrofit.Builder()
        .baseUrl(CommonConstants.HTTP_ROOT_URL)
        .client(httpClient.build())
        .addConverterFactory(GsonConverterFactory.create())


    private val retrofit = builder.build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)
}