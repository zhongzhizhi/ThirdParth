package com.thirdparty.common.basic

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.tbruyelle.rxpermissions3.RxPermissions
import com.thirdparty.common.ext.getViewBinding
import com.thirdparty.common.viewmodel.SharedViewModel


/**
 *  @项目名：  MeetingApp
 *  @包名：    com.dosmono.common.basic
 *  @文件名:   BaseFragment
 *  @创建者:   zer
 *  @创建时间:  2020/10/13 17:46
 *  @描述：    TODO
 */
abstract class BaseFragment<T: BaseViewModel>(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {
    private val mRxPermissions by lazy { RxPermissions(this) }
    protected var mActivity: AppCompatActivity? = null
    protected lateinit var mSharedViewModel: SharedViewModel
    private var mLoadingDialog: Dialog? = null
    protected var mViewModel: T? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    override fun onDetach() {
        super.onDetach()
        mActivity = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSharedViewModel = getAppViewModelProvider().get(SharedViewModel::class.java)
        mViewModel = createViewModel()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData(savedInstanceState)
    }

    open fun createViewModel(): T?{
        return null
    }

    protected fun showLoading(){
//        activity?.let {
//            if (null == mLoadingDialog) {
//                mLoadingDialog = DialogFactory.createLoadingDialog(mActivity, false)
//            }
//            if (mLoadingDialog?.isShowing == false) {
//                mLoadingDialog?.show()
//            }
//        }
    }

    protected fun hideLoading(){
        if (null != mLoadingDialog && mLoadingDialog?.isShowing == true) {
            mLoadingDialog?.dismiss()
        }
    }

    protected fun setTitle(str: String){
//        val left = view?.findViewById<LinearLayout>(R.id.ll_title_layout_back)
//        val content = view?.findViewById<TextView>(R.id.tv_title_layout_content)
//        content?.text = str
//        left?.visibility = View.GONE
    }

    protected fun setTitle(str: String,block: (view: View) -> Unit){
//        val left = view?.findViewById<LinearLayout>(R.id.ll_title_layout_back)
//        val content = view?.findViewById<TextView>(R.id.tv_title_layout_content)
//        content?.text = str
//        left?.visibility = View.VISIBLE
//        left?.antiShakeClickListener(block)
    }

    fun getAppViewModelProvider(): ViewModelProvider {
        return CommonApp.getInstance().getAppViewModelProvider(mActivity!!)
    }

    protected fun requestPermission(vararg permissions: String,block: (granted: Boolean)->Unit){
        val p = mRxPermissions.requestEachCombined(*permissions)
            .subscribe {
                if (it.granted){
                    block(true)
                }else{
                    block(false)
                    if (!it.shouldShowRequestPermissionRationale){
                        showRequestPermissionRationale()
                    }
                }
            }
    }

    private fun showRequestPermissionRationale(){
//        DialogFactory.createCommonDialog(context,
//            getString(R.string.meet_common_dialog_tips),
//            getString(R.string.meet_common_permission_handle_open),
//            getString(R.string.meet_common_dialog_btn_cancel),
//            getString(R.string.meet_common_permission_go_setting)) { dialog, view ->
//
//            when(view.id){
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


    abstract fun initData(savedInstanceState: Bundle?)

    override fun onDestroy() {
        super.onDestroy()
        mLoadingDialog = null
    }
}