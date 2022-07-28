package com.example.marveltest.domain

import com.example.marveltest.data.MarvelRepository
import com.example.marveltest.domain.model.MarvelCharacter
import javax.inject.Inject

class GetMarvelCharactersUseCase @Inject constructor(private val repository: MarvelRepository) {

    suspend operator fun invoke(page: kotlin.Int):List<MarvelCharacter>{
        return repository.getAllCharactersFromApi(page)
    }
}