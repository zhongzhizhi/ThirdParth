package com.thirdparty.common.basic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thirdparty.common.ext.toast
import com.thirdparty.common.network.exception.ExceptionHandle
import kotlinx.coroutines.*


/**
 *  @项目名：  MeetingApp
 *  @包名：    com.dosmono.common.basic
 *  @文件名:   BaseViewModel
 *  @创建者:   zer
 *  @创建时间:  2020/10/10 10:41
 *  @描述：    TODO
 */
open class BaseViewModel : ViewModel() {
    val isShowLoading: LiveData<Boolean>
        get() = _isShowLoading

    protected val _isShowLoading = MutableLiveData<Boolean>()

    fun launch(block: suspend ()->Unit,
               error: suspend (e: Throwable)->Unit = { ExceptionHandle.handleException(it).toast() },
               isLoading: Boolean = true)
            = viewModelScope.launch{
        try {
            if (isLoading)_isShowLoading.value = true
            block()
            if (isLoading)_isShowLoading.value = false
        }catch (e: Throwable){
            error(e)
            if (isLoading)_isShowLoading.value = false
        }
    }


}