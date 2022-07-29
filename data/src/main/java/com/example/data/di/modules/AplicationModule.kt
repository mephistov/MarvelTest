package com.example.data.di.modules

import com.example.data.repository.MarvelRepositoryImp
import com.example.domain.repository.MarvelRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AplicationModule {

    @Binds
    abstract fun bindsMarvelRepository(repoImpl: MarvelRepositoryImp): MarvelRepository

}