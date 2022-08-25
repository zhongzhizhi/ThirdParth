package com.thirdparty.happychat.ui.fragment

import android.os.Bundle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.thirdparty.common.basic.BaseFragment
import com.thirdparty.common.basic.BaseViewModel
import com.thirdparty.common.ext.bindView
import com.thirdparty.happychat.R
import com.thirdparty.happychat.databinding.FragmentHomeTabBinding


/**
 *  @项目名：  HappyChat
 *  @包名：    com.thirdparty.happychat.ui.fragment
 *  @文件名:   HomeTabFragment
 *  @创建者:   Administrator
 *  @创建时间:  2021/10/22 16:47
 *  @描述：    TODO
 */
class HomeTabFragment : BaseFragment<BaseViewModel>(R.layout.fragment_home_tab) {

    private val mViewBinding: FragmentHomeTabBinding by bindView()

    override fun initData(savedInstanceState: Bundle?) {
        initViewPager()
        mViewBinding.navView.setOnNavigationItemSelectedListener(onNavigationItemSelected)
    }

    private val onNavigationItemSelected = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.home -> {
                switchFragment(0)
            }
            R.id.blog -> {
                switchFragment(1)
            }
            R.id.search -> {
                switchFragment(2)
            }
            R.id.project -> {
                switchFragment(3)
            }
            R.id.profile -> {
                switchFragment(4)
            }
        }
        true
    }

    private fun switchFragment(position: Int): Boolean {
        mViewBinding.mainViewpager.setCurrentItem(position, false)
        return true
    }

    private fun initViewPager() {
        mViewBinding.mainViewpager.isUserInputEnabled = false
        mViewBinding.mainViewpager.offscreenPageLimit = 2
        mViewBinding.mainViewpager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int) = when (position) {
                0 -> MessageFragment()
                1 -> MessageFragment()
                2 -> MessageFragment()
                3 -> MessageFragment()
                4 -> MessageFragment()
                else -> MessageFragment()
            }

            override fun getItemCount() = 5
        }
    }
}