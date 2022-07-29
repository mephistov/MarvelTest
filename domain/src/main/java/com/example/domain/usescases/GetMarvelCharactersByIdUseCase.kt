package com.example.domain.usescases

import com.example.domain.entities.MarvelCharacter
import com.example.domain.repository.MarvelRepository

import javax.inject.Inject

class GetMarvelCharactersByIdUseCase @Inject constructor(private val repository: MarvelRepository) {
    suspend operator fun invoke(id: Int):List<MarvelCharacter>{
        return repository.getCharactersByIdFromApi(id)
    }
}