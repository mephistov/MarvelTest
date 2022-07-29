package com.example.data.mappers

import com.example.data.model.ResultCharacters
import com.example.domain.entities.MarvelCharacter


    internal fun ResultCharacters.toMarvelCharacter() = MarvelCharacter(
        this.id,
        this.name,
        this.description,
        "${this.thumbnail.path}.${this.thumbnail.extension}"
    )

