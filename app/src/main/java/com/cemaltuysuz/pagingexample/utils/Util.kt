package com.cemaltuysuz.pagingexample.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.cemaltuysuz.pagingexample.model.UserItem

@BindingAdapter("imageUrl")
fun AppCompatImageView.loadImage(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(this.context)
            .load(url)
            .centerCrop()
            .into(this)
    }
}