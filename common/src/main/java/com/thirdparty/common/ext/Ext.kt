package com.thirdparty.common.ext

import android.content.Context
import android.content.Intent
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.thirdparty.common.app.GlobalApplication
import com.thirdparty.common.util.AntiShakeUtil
import java.lang.reflect.ParameterizedType


/**
 *  @项目名：  MeetingApp
 *  @包名：    com.dosmono.common.ext
 *  @文件名:   Ext
 *  @创建者:   zer
 *  @创建时间:  2020/10/10 9:43
 *  @描述：    TODO
 */

private var mToast: Toast? = null

inline fun  <reified T> startActivity(context: Context){
    context.startActivity(Intent(context,T::class.java))
}

inline fun <reified T> startActivity(context: Context,block: Intent.()->Unit){
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}

inline fun View.antiShakeClickListener(crossinline block: (view: View)->Unit){
    setOnClickListener {
        if (!AntiShakeUtil.isShakeWith(id)){
            block(this)
        }
    }
}

fun String.toast(context: Context = GlobalApplication.getApplicationContext(), duration: Int = Toast.LENGTH_SHORT){
    if (mToast == null){
        mToast = Toast.makeText(context,this,duration)
    }else{
        mToast?.setText(this)
    }
    mToast?.show()
}

fun Int.toast(context: Context = GlobalApplication.getApplicationContext(),duration: Int = Toast.LENGTH_SHORT){
    if (mToast == null){
        mToast = Toast.makeText(context,this,duration)
    }else{
        mToast?.setText(this)
    }
    mToast?.show()
}

fun Int.dip2px(): Int {
//    val density = DosmonoApplication.getDosmonoContext().resources.displayMetrics.density
//    return (this*density+0.5f).toInt()

    return (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,this+0.5f,GlobalApplication.getApplicationContext().resources.displayMetrics)+0.5f).toInt()
}

fun Int.px2dip(): Int{
//    val density = DosmonoApplication.getDosmonoContext().resources.displayMetrics.density
//    return (this/density+0.5f).toInt()

    return (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,this+0.5f,GlobalApplication.getApplicationContext().resources.displayMetrics)+0.5f).toInt()
}

fun Int.sp2px(): Float{
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,this+0.5f,GlobalApplication.getApplicationContext().resources.displayMetrics)+0.5f
}

fun Int.px2sp(): Float{
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,this+0.5f,GlobalApplication.getApplicationContext().resources.displayMetrics)+0.5f
}

inline fun <VB: ViewBinding> Any.getViewBinding(inflater: LayoutInflater): VB{
    val vbClazz = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.filterIsInstance<Class<VB>>()
    val inflaterMethod = vbClazz[0].getDeclaredMethod("inflate",LayoutInflater::class.java)
    return inflaterMethod.invoke(null,inflater) as VB
}

inline fun <VB: ViewBinding> Any.getViewBinding(inflater: LayoutInflater,container: ViewGroup): VB{
    val vbClazz = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.filterIsInstance<Class<VB>>()
    val inflaterMethod = vbClazz[0].getDeclaredMethod("inflate",LayoutInflater::class.java,ViewGroup::class.java,Boolean::class.java)
    return inflaterMethod.invoke(null,inflater,container,false) as VB
}