package com.thirdparty.common.network.exception

/**
 *  @项目名：  meeting
 *  @包名：    com.dosmono.meetcommon.http.exception
 *  @文件名:   ApiException
 *  @创建者:   zer
 *  @创建时间:  2020/4/16 10:03
 *  @描述：    TODO
 */
class ApiException : RuntimeException {

    var code: Int = ErrorStatus.SUCCESS
        private set

    constructor(throwable: Throwable, code: Int) : super(throwable) {
        this.code = code
    }

    constructor(message: String) : super(Throwable(message))
}