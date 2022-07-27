package com.example.marveltest.data.model

import com.google.gson.annotations.SerializedName

data class MarvelModel(
    val attributionHTML: String = "",
    val attributionText: String = "",
    val code: Int = 0,
    val copyright: String = "",
    @SerializedName("data") val dataMarvel: DataMarvel? = null,
    val etag: String = "",
    val status: String = ""
)