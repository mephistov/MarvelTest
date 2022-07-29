package com.example.data.model

import com.google.gson.annotations.SerializedName

data class DataMarvel(
    val count: Int,
    val limit: Int,
    val offset: Int,
    @SerializedName("results") val resultCharacters: List<ResultCharacters>,
    val total: Int
)