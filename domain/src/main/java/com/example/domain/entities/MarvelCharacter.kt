package com.example.domain.entities

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.domain.R


data class MarvelCharacter(
    val id:Int,
    val name:String,
    var description: String,
    val thumnail: String
)

@BindingAdapter("descriptionCharacter")
fun changeDescription(description: TextView, descr:String?){
    if(descr == "")
        description.text = description.context.getString(R.string.noData)
    else
        description.text = descr
}

@BindingAdapter("imageMarvel")
fun setImage(image: ImageView, url:String?){
    Glide
        .with(image.context)
        .load(url)
        .placeholder(R.drawable.loading_marvel)
        .into(image)
}


