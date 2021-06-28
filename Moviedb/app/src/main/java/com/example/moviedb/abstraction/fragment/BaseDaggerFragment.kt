package com.example.moviedb.abstraction.fragment

import android.os.Bundle
import androidx.viewbinding.ViewBinding

abstract class BaseDaggerFragment<viewBinding: ViewBinding>: BaseFragment<viewBinding>() {
    protected abstract fun initInjector()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInjector()
    }


}