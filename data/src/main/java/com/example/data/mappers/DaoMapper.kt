package com.example.data.mappers

import com.example.data.local.entities.MarvelCharacterEntity
import com.example.domain.entities.MarvelCharacter

internal fun MarvelCharacter.toDatabase(page: Int) = MarvelCharacterEntity(
    id = id,
    name = name,
    description = description,
    imageUrl = thumnail,
    page = page
)