package com.example.marveltest.data

import com.example.marveltest.data.model.MarvelModel
import com.example.marveltest.data.network.MarvelService
import com.example.marveltest.domain.model.MarvelCharacter
import com.example.marveltest.domain.model.toMarvelCharacter
import javax.inject.Inject

class MarvelRepository @Inject constructor(
    private val api: MarvelService
){
    suspend fun getAllCharactersFromApi(page: Int): List<MarvelCharacter> {
        val response: MarvelModel = api.getCharacters(page)
        val dataMarvel = response.dataMarvel
        if(dataMarvel != null){
            return dataMarvel.resultCharacters.map { it.toMarvelCharacter() }
        }

        return emptyList()
    }

    suspend fun getCharactersByIdFromApi(id:Int):List<MarvelCharacter>{
        val response: MarvelModel = api.getCharacterById(id)
        val dataMarvel = response.dataMarvel
        if(dataMarvel != null){
            return dataMarvel.resultCharacters.map { it.toMarvelCharacter() }
        }

        return emptyList()
    }
}