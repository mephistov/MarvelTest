package com.example.marveltest.domain.model

import com.example.marveltest.data.model.MarvelModel
import com.example.marveltest.data.model.ResultCharacters

data class MarvelCharacter(
    val id:Int,
    val name:String,
    val description: String,
    val thumnail: String
)

fun ResultCharacters.toMarvelCharacter() = MarvelCharacter(
    this.id,
    this.name,
    this.description,
    "${this.thumbnail.path}.${this.thumbnail.extension}"
)