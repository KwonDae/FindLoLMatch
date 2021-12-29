package com.example.testriotapi.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * @author Daewon
 * @package com.example.testriotapi.databinding
 * @email green201402317@gmail.com
 * @created 2021/12/30
 */

object DataBindingAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindImage(imageView: ImageView, imageUrl: String?) {
        imageUrl?.let {
            Glide.with(imageView).load(imageUrl).centerCrop().into(imageView)
        }
    }
}