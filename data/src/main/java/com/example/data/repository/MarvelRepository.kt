package com.example.data.repository

import com.example.data.mappers.toMarvelCharacter
import com.example.data.model.MarvelModel
import com.example.data.network.MarvelService
import com.example.domain.entities.MarvelCharacter
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