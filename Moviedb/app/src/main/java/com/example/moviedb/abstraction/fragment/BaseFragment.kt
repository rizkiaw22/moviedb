package com.example.moviedb.abstraction.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<viewBinding: ViewBinding>: Fragment() {

    protected abstract fun getFragmentViewBinding():viewBinding
    lateinit var binding: ViewBinding

    protected abstract fun showLoading()
    protected abstract fun hideLoading()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=getFragmentViewBinding()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

    fun openActivity(clazz: Class<*>, canBack: Boolean = true) {
        activity?.let {
            it.startActivity(Intent(it, clazz))
            if (!canBack) {
                it.finish()
            }
        }
    }

    fun openActivityWithResult(clazz: Class<*>,reqCode:Int){
        startActivityForResult(Intent(context, clazz),reqCode)
    }

    fun openIntent(intent: Intent){
        intent?.let{
            startActivity(it)
        }
    }

    fun showAlertDialog(title: String,message: String,context: Context){
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK"){_, _ ->
        }
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }
}