package com.mamudo.challengetheheroes.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("android:src")
fun loadImageWithPicasso(imageView: AppCompatImageView, url: String) {
    Picasso.get().load(url).into(imageView)
}