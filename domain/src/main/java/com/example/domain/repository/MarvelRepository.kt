package com.example.domain.repository

import com.example.domain.entities.MarvelCharacter

interface MarvelRepository {
    suspend fun getAllCharactersFromApi(page: Int): List<MarvelCharacter>
    suspend fun getCharactersByIdFromApi(id: Int): List<MarvelCharacter>

    suspend fun saveCharactersFromApi(listChar: List<MarvelCharacter>, page: Int):Boolean
    suspend fun saveCharacter():Boolean
    suspend fun getAllCharactersFromLocal(page: Int): List<MarvelCharacter>
    suspend fun getCharacterByIdFromLocal(id:Int) :MarvelCharacter
}