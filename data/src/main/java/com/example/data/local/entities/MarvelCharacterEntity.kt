package com.example.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "marvel_table")
data class MarvelCharacterEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val imageUrl: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "page") val page: Int = 0

)
