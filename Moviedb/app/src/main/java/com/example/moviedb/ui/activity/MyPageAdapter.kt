package com.example.moviedb.ui.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyPageAdapter(supportFragmentManager: FragmentManager): FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val mFragmenlist = ArrayList<Fragment>()
    private val mFragmentTitleList=ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return mFragmenlist[position]
    }

    override fun getCount(): Int {
        return mFragmenlist.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }
    fun addFragment (fragment: Fragment, title:String){
        mFragmenlist.add(fragment)
        mFragmentTitleList.add(title)
    }
}