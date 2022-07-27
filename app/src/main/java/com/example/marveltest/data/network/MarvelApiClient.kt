package com.example.marveltest.data.network

import com.example.marveltest.data.model.MarvelModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApiClient {

    @GET("/v1/public/characters")
    suspend fun getMarvelCharacters(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): Response<MarvelModel>


    @GET("/v1/public/characters/{characterId}")
    suspend fun getMarvelCharacterById(
        @Path("characterId") characterId: Int,
        @Query("ts") ts: String? = null,
        @Query("apikey") apikey: String? = null,
        @Query("hash") hash: String? = null
    ): Response<MarvelModel>
}