package com.thirdparty.common.network.exception

import com.dosmono.logger.Logger
import com.thirdparty.common.R
import com.thirdparty.common.app.GlobalApplication
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

/**
 *  @项目名：  meeting
 *  @包名：    com.dosmono.meetcommon.http.exception
 *  @文件名:   ExceptionHandle
 *  @创建者:   zer
 *  @创建时间:  2020/4/16 10:03
 *  @描述：    TODO
 */
class ExceptionHandle {
    companion object {
        private const val TAG = "ExceptionHandle"
        private var errorCode = ErrorStatus.UNKNOWN_ERROR
        private var errorMsg = GlobalApplication.getApplicationContext().getString(R.string.common_http_exception1)

        fun handleException(e: Throwable): String {
            e.printStackTrace()
            if (e is SocketTimeoutException
                    || e is ConnectException
                    || e is HttpException
            ) { //均视为网络错误
                Logger.e("网络连接异常: ${e.message}")
                errorMsg = GlobalApplication.getApplicationContext().getString(R.string.common_http_exception2)
                errorCode = ErrorStatus.NETWORK_ERROR
            } else if (/*e is JsonParseException ||*/ e is JSONException
                    || e is ParseException
            ) {   //均视为解析错误
                Logger.e("数据解析异常: ${e.message}")
                errorMsg = GlobalApplication.getApplicationContext().getString(R.string.common_http_exception3)
                errorCode = ErrorStatus.SERVER_ERROR
            } else if (e is ApiException) {//服务器返回的错误信息
                errorMsg = e.message.toString()
                errorCode = ErrorStatus.SERVER_ERROR
            } else if (e is UnknownHostException) {
                Logger.e("网络连接异常: ${e.message}")
                errorMsg = GlobalApplication.getApplicationContext().getString(R.string.common_http_exception2)
                errorCode = ErrorStatus.NETWORK_ERROR
            } else if (e is IllegalArgumentException) {
                errorMsg = GlobalApplication.getApplicationContext().getString(R.string.common_http_exception4)
                errorCode = ErrorStatus.SERVER_ERROR
            } else {//未知错误
                try {
                    Logger.e("错误: ${e.message}")
                } catch (e1: Exception) {
                    Logger.e("未知错误Debug调试: ${e.message}")
                }
                errorMsg = GlobalApplication.getApplicationContext().getString(R.string.common_http_exception5)
                errorCode = ErrorStatus.UNKNOWN_ERROR
            }
            return errorMsg
        }

    }
}