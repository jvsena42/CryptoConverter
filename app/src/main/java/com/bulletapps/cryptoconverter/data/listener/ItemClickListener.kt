package com.bulletapps.cryptoconverter.data.listener

import android.widget.ImageView
import com.google.android.material.card.MaterialCardView

interface ItemClickListener<T> {

    fun onClick(item:T)
}