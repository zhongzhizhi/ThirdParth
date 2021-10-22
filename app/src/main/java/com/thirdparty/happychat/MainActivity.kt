package com.thirdparty.happychat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.thirdparty.common.basic.BaseActivity
import com.thirdparty.common.basic.BaseViewModel

class MainActivity : BaseActivity<BaseViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}