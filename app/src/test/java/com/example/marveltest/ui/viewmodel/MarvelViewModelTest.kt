package com.example.marveltest.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marveltest.domain.GetMarvelCharactersByIdUseCase
import com.example.marveltest.domain.GetMarvelCharactersUseCase
import com.example.marveltest.domain.model.MarvelCharacter
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MarvelViewModelTest{

    @RelaxedMockK
    private lateinit var getMarvelCharactersUseCase: GetMarvelCharactersUseCase
    @RelaxedMockK
    private lateinit var getMarvelCharactersByIdUseCase: GetMarvelCharactersByIdUseCase

    private lateinit var viewModel: MarvelViewModel
    private val page = 0

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        viewModel = MarvelViewModel(getMarvelCharactersUseCase, getMarvelCharactersByIdUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when GetMarvelCharactersUseCase return a list set on the livedata`() = runTest {
        //Given
        val listCharacters = listOf<MarvelCharacter>(
            MarvelCharacter(1,"name1","desc 1","image1"),
            MarvelCharacter(2,"name2","desc 2","image2"),
        )
        coEvery { getMarvelCharactersUseCase(page) } returns listCharacters

        //When
        viewModel.getAllMarvelCharacters(page)

        //Then
        assert(viewModel.charactersListModel.value == listCharacters)
        assert(viewModel.isLoading.value == false)
    }
    @Test
    fun `when GetMarvelCharactersUseCase doesnt return a empty list livedata`() = runTest {
        //Given
        val listCharacters = emptyList<MarvelCharacter>()
        coEvery { getMarvelCharactersUseCase(page) } returns listCharacters

        //When
        viewModel.getAllMarvelCharacters(page)

        //Then
        assert(viewModel.charactersListModel.value == null)
        assert(viewModel.isLoading.value == false)
    }
    //-------------- GetMarvelCharactersByIdUseCase ------
    @Test
    fun `when GetMarvelCharactersByIdUseCase return a list set on the livedata`() = runTest {
        //Given
        val listCharacters = listOf<MarvelCharacter>(
            MarvelCharacter(1,"name1","desc 1","image1")
        )
        coEvery { getMarvelCharactersByIdUseCase(123) } returns listCharacters

        //When
        viewModel.getCharacterById(123)

        //Then
        assert(viewModel.characterModel.value == listCharacters.first())
        assert(listCharacters.size == 1)
        assert(viewModel.isLoading.value == false)
    }
    @Test
    fun `when GetMarvelCharactersByIdUseCase doesnt return a empty list livedata`() = runTest {
        //Given
        val listCharacters = emptyList<MarvelCharacter>()
        coEvery { getMarvelCharactersByIdUseCase(123) } returns listCharacters

        //When
        viewModel.getCharacterById(123)

        //Then
        assert(viewModel.characterModel.value == null)
        assert(viewModel.isLoading.value == false)
    }

}