package com.thirdparty.common.network.exception

/**
 *  @项目名：  meeting
 *  @包名：    com.dosmono.meetcommon.http.exception
 *  @文件名:   ErrorStatus
 *  @创建者:   zer
 *  @创建时间:  2020/4/16 10:03
 *  @描述：    TODO
 */
object ErrorStatus {
    //响应成功
    const val SUCCESS = 8000
    //账号注销失败，金额未介算清
    const val ACCOUNT_DESTROY_ERROR = 8001
    //Token 过期
    const val TOKEN_INVALID = 401
    //未知错误
    const val UNKNOWN_ERROR = 1002
    //服务器内部错误
    const val SERVER_ERROR = 1003
    //网络连接超时
    const val NETWORK_ERROR = 1004
    //API解析异常（或者第三方数据结构更改）等其他异常
    const val API_ERROR = 1005
    //单点登录冲突
    const val LOGIN_CONFLICT = 7777
    //已存在
    const val ALREADY_EXISTS = 9001
    //未授权
    const val UNAUTHORIZED = 9008
    //操作上限
    const val OPERATION_LIMIT = 7703
    //记录已经存在
    const val RECORD_ALREADY_EXISTS = 9001
    //记录不存在
    const val RECORD_DOES_NOT_EXIST = 9002
    //账号已经被禁用
    const val ACCOUNT_HAS_DISABLED = 7755
}