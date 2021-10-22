package com.thirdparty.common.config


/**
 *  @项目名：  HappyChat
 *  @包名：    com.thirdparty.common.config
 *  @文件名:   Error
 *  @创建者:   Administrator
 *  @创建时间:  2021/10/20 18:00
 *  @描述：    TODO
 */
object Error {

    const val ERR_NONE = 0

    const val ERR_COMMON_BASE = 1000

    const val ERR_JSON_SERIALIZE = ERR_COMMON_BASE + 1 //JSON 序列化失败

    const val ERR_JSON_DESERIALIZE = ERR_COMMON_BASE + 2 //JSON 反序列化失败

    const val ERR_DOWNLOAD_INMOBILE = ERR_COMMON_BASE + 3 //下载时处于移动运营商数据网络

    const val ERR_NO_INTERNET = ERR_COMMON_BASE + 4 //当前无网络

    const val ERR_INVALID_PARAMS = ERR_COMMON_BASE + 5 //非法参数

    const val ERR_REPLY_TIMEOUT = ERR_COMMON_BASE + 6 //响应超时

    const val ERR_NETWORK_EXCEPTION = ERR_COMMON_BASE + 7 //网络异常

    const val ERR_REPLY_NULL = ERR_COMMON_BASE + 8 //响应结果为空

    const val ERR_NOT_INIT = ERR_COMMON_BASE + 9 //没有初始化


    const val ERR_HTTP_BASE = 9000

    const val HTTP_SUCCESS = 8000 //成功

    const val ERR_HTTP_FAIL = ERR_HTTP_BASE + 0 //服务器内部错误

    const val ERR_HTTP_RECORDEXISTS = ERR_HTTP_BASE + 1 //记录已存在

    const val ERR_HTTP_RECORDNOTEXISTS = ERR_HTTP_BASE + 2 //记录不存在

    const val ERR_HTTP_OPFAIL = ERR_HTTP_BASE + 4 //操作失败

    const val ERR_HTTP_PARAMNOTEMPTY = ERR_HTTP_BASE + 5 //参数不能为空

    const val ERR_HTTP_ILLEGALPARAM = ERR_HTTP_BASE + 6 //非法参数

    const val ERR_HTTP_VALIDATEFAIL = ERR_HTTP_BASE + 7 //验证失败

    const val ERR_HTTP_NOPERMISSION = ERR_HTTP_BASE + 8 //没有权限

    const val ERR_HTTP_OPFREQUENT = ERR_HTTP_BASE + 100 //操作频繁


}