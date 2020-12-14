package com.example.projectandroid.network

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.projectandroid.R


// akkor hajtodik v;gre amikor egz XML objektum rendelkezik az img_src attributummal
@BindingAdapter("img_src")
fun bindImage(imgView: ImageView, imgUrl: String?) {
//az Imageiew osztaly haszn'lhatsa ezt az adaptert
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        //Glide lncolt hívásokat használ olvasható szintaxissal
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

