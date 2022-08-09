package com.example.data.repository

import com.example.data.local.dao.MarvelDao
import com.example.data.mappers.toDatabase
import com.example.data.mappers.toMarvelCharacter
import com.example.data.model.MarvelModel
import com.example.data.network.MarvelService
import com.example.domain.entities.MarvelCharacter
import com.example.domain.repository.MarvelRepository
import javax.inject.Inject

class MarvelRepositoryImp @Inject constructor(
    private val api: MarvelService,
    private val database: MarvelDao
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

    override suspend fun saveCharactersFromApi(listChar: List<MarvelCharacter>, page: Int): Boolean {
        database.insertAll(listChar.map { it.toDatabase(page) })
        return true //si todo sale bien
    }

    override suspend fun saveCharacter(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCharactersFromLocal(page: Int): List<MarvelCharacter> {
        return database.getAllMarvelCharacters(page).map { it.toMarvelCharacter() }
    }


    override suspend fun getCharacterByIdFromLocal(id: Int): MarvelCharacter {
        TODO("Not yet implemented")
    }
}