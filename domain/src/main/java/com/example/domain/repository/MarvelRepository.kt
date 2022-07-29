package com.example.domain.repository

import com.example.domain.entities.MarvelCharacter

interface MarvelRepository {
    suspend fun getAllCharactersFromApi(page: Int): List<MarvelCharacter>
    suspend fun getCharactersByIdFromApi(id: Int): List<MarvelCharacter>
}