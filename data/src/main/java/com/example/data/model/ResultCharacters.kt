package com.example.data.model

data class ResultCharacters(
    val comics: Comics? = null,
    val description: String,
    val events: Events? = null,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series? = null,
    val stories: Stories? = null,
    val thumbnail: Thumbnail,
    val urls: List<Url>? = null
)