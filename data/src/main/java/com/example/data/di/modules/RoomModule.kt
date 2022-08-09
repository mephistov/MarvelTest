package com.example.data.di.modules

import android.content.Context
import androidx.room.Room
import com.example.data.local.MarvelDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val QUOTE_DATABASE_NAME = "marvel_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MarvelDatabase::class.java, QUOTE_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideMarvelDao(db: MarvelDatabase) = db.getMarvelDao()
}