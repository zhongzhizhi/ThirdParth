package com.thirdparty.common.basic

import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.provider.Settings
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.tbruyelle.rxpermissions3.RxPermissions
import com.thirdparty.common.util.AppLanguageUtil
import com.thirdparty.common.util.KeyBoardUtil
import com.thirdparty.common.viewmodel.SharedViewModel


/**
 *  @项目名：  MeetingApp
 *  @包名：    com.dosmono.common.basic
 *  @文件名:   BaseActivity
 *  @创建者:   zer
 *  @创建时间:  2020/10/9 16:21
 *  @描述：    TODO
 */
open class BaseActivity<T : BaseViewModel> : AppCompatActivity() {
    private val mRxPermissions by lazy { RxPermissions(this) }
    protected lateinit var mSharedViewModel: SharedViewModel
    private var mLoadingDialog: Dialog? = null
    protected var mViewModel: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        mSharedViewModel = getAppViewModelProvider().get(SharedViewModel::class.java)

        mViewModel = createViewModel()
        mViewModel?.isShowLoading?.observe(this, Observer {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        })
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(AppLanguageUtil.attachBaseContext(newBase))
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    open fun createViewModel(): T? {
        return null
    }

    protected fun setTitle(str: String, leftVisible: Boolean) {
//        val left = findViewById<LinearLayout>(R.id.ll_title_layout_back)
//        val content = findViewById<TextView>(R.id.tv_title_layout_content)
//        content?.text = str
//        if (leftVisible) {
//            left?.visibility = View.VISIBLE
//            left?.setOnClickListener { finish() }
//        } else {
//            left?.visibility = View.GONE
//        }
    }

    protected fun showLoading() {
//        if (null == mLoadingDialog) {
//            mLoadingDialog = DialogFactory.createLoadingDialog(this, false)
//        }
//        if (!mLoadingDialog!!.isShowing) {
//            mLoadingDialog!!.show()
//        }
    }

    protected fun hideLoading() {
        if (null != mLoadingDialog && mLoadingDialog!!.isShowing) {
            mLoadingDialog!!.dismiss()
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_UP) {
            val view = currentFocus
            if (KeyBoardUtil.isHideKeyboard(view, ev)) {
                KeyBoardUtil.hideKeyBoard(this, view)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun getAppViewModelProvider(): ViewModelProvider {
        return CommonApp.getInstance().getAppViewModelProvider(this)
    }


    protected fun requestPermission(vararg permissions: String, block: (granted: Boolean) -> Unit) {
        mRxPermissions.requestEachCombined(*permissions)
            .subscribe {
                if (it.granted) {
                    block(true)
                } else {
                    block(false)
                    if (!it.shouldShowRequestPermissionRationale) {
                        showRequestPermissionRationale()
                    }
                }
            }
    }

    private fun showRequestPermissionRationale() {
//        DialogFactory.createCommonDialog(
//            this,
//            getString(R.string.meet_common_dialog_tips),
//            getString(R.string.meet_common_permission_handle_open),
//            getString(R.string.meet_common_dialog_btn_cancel),
//            getString(R.string.meet_common_permission_go_setting)
//        ) { dialog, view ->
//
//            when (view.id) {
//                R.id.tv_common_dialog_cancel -> {
//                    dialog.dismiss()
//                }
//                R.id.tv_common_dialog_sure -> {
//                    dialog.dismiss()
//                    Intent(Settings.ACTION_SETTINGS).apply {
//                        startActivity(this)
//                    }
//                }
//            }
//
//        }.show()
    }
}