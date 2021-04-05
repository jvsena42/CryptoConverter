package com.bulletapps.cryptoconverter.data.util

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import com.google.android.material.snackbar.Snackbar


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

// Snackbar Extensions
fun View.showSnackbarRed(message : String){
    val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snackBar.setBackgroundTint(Color.RED)
    snackBar.show()
}

fun View.showSnackbar(message : String){
    val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
    snackBar.show()
}

fun View.snackBarWithAction(message : String, actionLabel : String,
                            block : () -> Unit){
    Snackbar.make(this, message, Snackbar.LENGTH_LONG)
            .setAction(actionLabel) {
                block()
            }
}

