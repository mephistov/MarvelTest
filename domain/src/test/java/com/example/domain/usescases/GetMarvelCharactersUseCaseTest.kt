package com.example.domain.usescases

import com.example.domain.entities.MarvelCharacter
import com.example.domain.repository.MarvelRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetMarvelCharactersUseCaseTest{

    @RelaxedMockK
    private lateinit var repositoryImp: MarvelRepository
    lateinit var getMarvelCharactersUseCase: GetMarvelCharactersUseCase
    private val page = 0

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getMarvelCharactersUseCase = GetMarvelCharactersUseCase(repositoryImp)
    }

    @Test
    fun `when the api doesnt return anything`() = runBlocking {
        val emptyLisy = emptyList<MarvelCharacter>()
        //Given
        coEvery { repositoryImp.getAllCharactersFromApi(page) } returns emptyLisy

        //When
        val response = getMarvelCharactersUseCase(page)

        //Then
        coVerify(exactly = 1) { repositoryImp.getAllCharactersFromApi(page) }
        assert(response == emptyLisy)
    }
    @Test
    fun `when the api return something then get values from api`() = runBlocking {
        //Given
        val myList = listOf(MarvelCharacter(12345, "AristiDevs","some description","imagen.png"))
        coEvery { repositoryImp.getAllCharactersFromApi(page) } returns myList

        //When
        val response = getMarvelCharactersUseCase(page)

        //Then
        coVerify(exactly = 1) { repositoryImp.getAllCharactersFromApi(page) }
        assert(response == myList)
    }

}