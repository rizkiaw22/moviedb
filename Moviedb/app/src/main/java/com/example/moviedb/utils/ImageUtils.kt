package com.example.moviedb.utils

import android.content.Context
import android.graphics.Bitmap
import android.util.Base64
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.example.moviedb.R
import java.io.ByteArrayOutputStream

object ImageUtils {

    fun loadImage(context: Context, imageView: ImageView, url: String?) {
        if (url != null && url.isNotEmpty()) {
            Glide.with(context)
                .asBitmap()
                .load(url)
                .fitCenter()
                .dontAnimate()
                .into(imageView)
        }
    }

    fun loadImageCircle(context: Context, imageView: ImageView, url: String?) {
        if (url != null && url.isNotEmpty()) {
            Glide.with(context)
                .asBitmap()
                .load(url)
                .fitCenter()
                .dontAnimate()
                .placeholder(ContextCompat.getDrawable(context, R.drawable.shape_circle_green))
                .into(getCircleImageViewTarget(imageView))
        }
    }

    fun loadImageCircle(context: Context, imageView: ImageView, bitmap: Bitmap) {
        Glide.with(context)
            .asBitmap()
            .load(bitmap)
            .dontAnimate()
            .fitCenter()
            .placeholder(ContextCompat.getDrawable(context, R.drawable.shape_circle_green))
            .into(getCircleImageViewTarget(imageView))
    }

    fun convertToString(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    private fun getCircleImageViewTarget(imageView: ImageView): BitmapImageViewTarget {
        return object : BitmapImageViewTarget(imageView) {
            override fun setResource(resource: Bitmap?) {
                val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(imageView.context.resources, resource)
                circularBitmapDrawable.isCircular = true
                imageView.setImageDrawable(circularBitmapDrawable)
            }
        }
    }


}