package com.example.moviedb.abstraction.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.example.moviedb.utils.UserSession

abstract class BaseActivity<viewBinding: ViewBinding>: AppCompatActivity() {

    protected abstract fun getViewBinding(): viewBinding
    lateinit var binding: viewBinding
    lateinit var userSession: UserSession

    val FINE_LOCATION_RQ = 101
    val COASE_LOCATION_RQ = 102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        userSession = UserSession(this)
    }

    fun changeStatusBarColor(@ColorRes color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, color)
        }
    }

    fun openActivity(clazz: Class<*>, canBack: Boolean = true) {
        startActivity(Intent(this, clazz))
        if (!canBack) {
            finish()
        }
    }
    fun openActivityToResult(returnIntent: Intent, responseCode:Int) {
        setResult(responseCode,returnIntent)
        finish()
    }
    fun openIntent(intent: Intent){
        intent?.let{
            startActivity(it)
        }
    }

    fun openActivityWithResult(clazz: Class<*>,reqCode:Int){
        startActivityForResult(Intent(this, clazz),reqCode)
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showLongMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun permissionLoader(){

    }
    private fun showPermissionDialog(permission: String,name:String,requestCode: Int,activity: Activity){
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setMessage("Dibutuhkan akses ${name}")
            setTitle("Izin Dibutuhkan")
            setPositiveButton("OK"){dialog,which->
                ActivityCompat.requestPermissions(activity, arrayOf(permission),requestCode)
            }
            val dialog: AlertDialog =builder.create()
            dialog.show()
        }
    }
    fun checkForPermissions(activity: Activity, permission:String, name:String, requestCode:Int){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            when{
                ContextCompat.checkSelfPermission(applicationContext,permission)== PackageManager.PERMISSION_GRANTED ->{
                    showMessage("Akses ${name} diizinkan")
                }
                shouldShowRequestPermissionRationale(permission) -> showPermissionDialog(permission,name,requestCode,activity)

                else-> ActivityCompat.requestPermissions(this, arrayOf(permission),requestCode)
            }
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        fun innerCheck(name: String){
            if(grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                showMessage("Izin Akses ${name} Ditolak")
            }else{
                showMessage("Akses ${name} Diizinkan")
            }
        }
        when(requestCode){
            FINE_LOCATION_RQ -> innerCheck("Lokasi")
            COASE_LOCATION_RQ -> innerCheck("Lokasi")
        }
    }
}
