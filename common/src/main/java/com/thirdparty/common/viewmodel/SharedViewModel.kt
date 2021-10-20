package com.thirdparty.common.viewmodel

import androidx.lifecycle.ViewModel
import com.thirdparty.common.bridge.UnPeekLiveData


/**
 *  @项目名：  MeetingApp
 *  @包名：    com.dosmono.common.viewmodel
 *  @文件名:   SharedViewModel
 *  @创建者:   zer
 *  @创建时间:  2020/10/10 9:40
 *  @描述：    TODO
 */
class SharedViewModel : ViewModel(){
    val meetHangup = UnPeekLiveData<Boolean>()
    val leaveChannel = UnPeekLiveData<Boolean>()
    val meetVideoFullScreen = UnPeekLiveData<Boolean>()
    val meetCancelVideoFullScreen = UnPeekLiveData<Boolean>()
    val meetLocalVideoUid = UnPeekLiveData<Int>()
    val logoutSuccess = UnPeekLiveData<Boolean>()
    val loginSuccess = UnPeekLiveData<Boolean>()
    val wxPaySuccess = UnPeekLiveData<Boolean>()

    val localMicOpen = UnPeekLiveData<Boolean>()
    val localVideoOpen = UnPeekLiveData<Boolean>()

    val localSpeakerOpen = UnPeekLiveData<Boolean>()

    val dialogueClickTitleBack = UnPeekLiveData<Boolean>()
}