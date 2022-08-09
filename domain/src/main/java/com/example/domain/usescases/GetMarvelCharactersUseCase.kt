package com.example.domain.usescases

import com.example.domain.entities.MarvelCharacter
import com.example.domain.repository.MarvelRepository
import javax.inject.Inject

class GetMarvelCharactersUseCase @Inject constructor(private val repository: MarvelRepository) {

    suspend operator fun invoke(page: Int):List<MarvelCharacter>{

        var listOfCharacters = repository.getAllCharactersFromLocal(page)
        if(listOfCharacters.isNullOrEmpty()){
            listOfCharacters = repository.getAllCharactersFromApi(page)
            if(!listOfCharacters.isNullOrEmpty())
                repository.saveCharactersFromApi(listOfCharacters,page)
        }


        return listOfCharacters
    }
}