package com.example.marveltest.domain

import com.example.marveltest.data.MarvelRepository
import com.example.marveltest.domain.model.MarvelCharacter
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetMarvelCharactersUseCaseTest{

    @RelaxedMockK
    private lateinit var repository: MarvelRepository
    lateinit var getMarvelCharactersUseCase: GetMarvelCharactersUseCase
    private val page = 0

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getMarvelCharactersUseCase = GetMarvelCharactersUseCase(repository)
    }

    @Test
    fun `when the api doesnt return anything`() = runBlocking {
        val emptyLisy = emptyList<MarvelCharacter>()
        //Given
        coEvery { repository.getAllCharactersFromApi(page) } returns emptyLisy

        //When
        val response = getMarvelCharactersUseCase(page)

        //Then
        coVerify(exactly = 1) { repository.getAllCharactersFromApi(page) }
        assert(response == emptyLisy)
    }
    @Test
    fun `when the api return something then get values from api`() = runBlocking {
        //Given
        val myList = listOf(MarvelCharacter(12345, "AristiDevs","some description","imagen.png"))
        coEvery { repository.getAllCharactersFromApi(page) } returns myList

        //When
        val response = getMarvelCharactersUseCase(page)

        //Then
        coVerify(exactly = 1) { repository.getAllCharactersFromApi(page) }
        assert(response == myList)
    }
}