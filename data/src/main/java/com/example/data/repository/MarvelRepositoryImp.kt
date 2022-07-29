package com.example.data.repository

import com.example.data.mappers.toMarvelCharacter
import com.example.data.model.MarvelModel
import com.example.data.network.MarvelService
import com.example.domain.entities.MarvelCharacter
import com.example.domain.repository.MarvelRepository
import javax.inject.Inject

class MarvelRepositoryImp @Inject constructor(
    private val api: MarvelService
):MarvelRepository{
    override suspend fun getAllCharactersFromApi(page: Int): List<MarvelCharacter> {
        val response: MarvelModel = api.getCharacters(page)
        val dataMarvel = response.dataMarvel
        if(dataMarvel != null){
            return dataMarvel.resultCharacters.map { it.toMarvelCharacter() }
        }

        return emptyList()
    }

    override suspend fun getCharactersByIdFromApi(id:Int):List<MarvelCharacter>{
        val response: MarvelModel = api.getCharacterById(id)
        val dataMarvel = response.dataMarvel
        if(dataMarvel != null){
            return dataMarvel.resultCharacters.map { it.toMarvelCharacter() }
        }

        return emptyList()
    }
}