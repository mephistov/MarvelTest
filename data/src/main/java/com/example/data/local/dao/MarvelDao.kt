package com.example.data.local.dao

import androidx.room.*
import com.example.data.local.entities.MarvelCharacterEntity

@Dao
interface MarvelDao {

    @Query("SELECT * FROM marvel_table WHERE page = :page")
    suspend fun getAllMarvelCharacters(page: Int):List<MarvelCharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characersList:List<MarvelCharacterEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertACharacter(character :MarvelCharacterEntity)

    @Delete
    fun delete(model: MarvelCharacterEntity)

    @Query("DELETE FROM marvel_table where id = :id")
    suspend fun deleteCharacterById(id:Int)
}