package com.example.moviedb.abstraction.activity

import android.os.Bundle
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.moviedb.R


abstract class BaseDaggerActivity<viewBinding:ViewBinding>: BaseActivity<viewBinding>() {

    @Nullable
    protected abstract fun fragment(): Fragment?

    protected abstract fun initInjector()
    protected abstract fun showLoading()
    protected abstract fun hideLoading()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInjector()
        if (savedInstanceState == null) {
            fragment()?.let {
                loadFragment(it)
            }
        }
    }

    open fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.viewPrent, fragment)
            .commit()
    }
}

