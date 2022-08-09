package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.MarvelDao
import com.example.data.local.entities.MarvelCharacterEntity

@Database(entities = [MarvelCharacterEntity::class], version = 1)
abstract class MarvelDatabase(): RoomDatabase() {
    abstract fun getMarvelDao(): MarvelDao
}