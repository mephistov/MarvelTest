package com.example.data.mappers

import com.example.data.local.entities.MarvelCharacterEntity
import com.example.data.model.ResultCharacters
import com.example.domain.entities.MarvelCharacter


internal fun ResultCharacters.toMarvelCharacter() = MarvelCharacter(
        this.id,
        this.name,
        this.description,
        "${this.thumbnail.path}.${this.thumbnail.extension}"
    )

internal fun MarvelCharacterEntity.toMarvelCharacter() = MarvelCharacter(
    id = id,
    name = name,
    description = description,
    thumnail = imageUrl
)

