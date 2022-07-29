package com.example.marveltest.domain

import com.example.domain.usescases.GetMarvelCharactersByIdUseCase
import com.example.data.repository.MarvelRepository
import com.example.domain.entities.MarvelCharacter
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetMarvelCharactersByIdUseCaseTest{
    @RelaxedMockK
    private lateinit var repository: MarvelRepository
    lateinit var getMarvelCharactersByIdUseCase: GetMarvelCharactersByIdUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getMarvelCharactersByIdUseCase = GetMarvelCharactersByIdUseCase(repository)
    }

    @Test
    fun `when the api doesnt return anything`() = runBlocking {
        val emptyLisy = emptyList<MarvelCharacter>()
        //Given
        coEvery { repository.getCharactersByIdFromApi(1234) } returns emptyLisy

        //When
        val response = getMarvelCharactersByIdUseCase(1234)

        //Then
        coVerify(exactly = 1) { repository.getCharactersByIdFromApi(1234) }
        assert(response == emptyLisy)
    }
    @Test
    fun `when the api return something then get values from api`() = runBlocking {
        //Given
        val myList = listOf(MarvelCharacter(12345, "AristiDevs","some description","imagen.png"))
        coEvery { repository.getCharactersByIdFromApi(1234) } returns myList

        //When
        val response = getMarvelCharactersByIdUseCase(1234)

        //Then
        coVerify(exactly = 1) { repository.getCharactersByIdFromApi(1234) }
        assert(response == myList)
    }
}