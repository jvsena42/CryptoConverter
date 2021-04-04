package com.bulletapps.cryptoconverter.data.util

import android.view.View
import android.widget.ImageView



/*fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .centerCrop()
        .into(this)
    }*/

fun View.viewGone(){
    this.visibility = View.GONE
}

fun View.viewVisible(){
    this.visibility = View.VISIBLE
}

