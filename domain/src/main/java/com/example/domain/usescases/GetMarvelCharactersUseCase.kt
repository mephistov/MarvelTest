package com.example.domain.usescases

import com.example.domain.entities.MarvelCharacter
import com.example.domain.repository.MarvelRepository
import javax.inject.Inject

class GetMarvelCharactersUseCase @Inject constructor(private val repository: MarvelRepository) {

    suspend operator fun invoke(page: Int):List<MarvelCharacter>{
        return repository.getAllCharactersFromApi(page)
    }
}