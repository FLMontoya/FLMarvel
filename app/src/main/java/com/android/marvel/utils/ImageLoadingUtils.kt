package com.android.marvel.utils

import android.widget.ImageView
import com.android.marvel.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

object ImageLoadingUtils {

    fun load(imageUrl: String, imageView: ImageView) {
        if (imageUrl.contains("image_not_available")) {
            Glide.with(imageView).load(R.drawable.ic_image_placeholder).into(imageView)
        } else {
            Glide.with(imageView).load(imageUrl).placeholder(R.drawable.ic_image_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE).centerInside().into(imageView)
        }
    }
}