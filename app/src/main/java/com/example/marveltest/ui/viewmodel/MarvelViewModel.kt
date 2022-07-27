package com.example.marveltest.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marveltest.domain.GetMarvelCharactersByIdUseCase
import com.example.marveltest.domain.GetMarvelCharactersUseCase
import com.example.marveltest.domain.model.MarvelCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarvelViewModel @Inject constructor(
    private val getMarvelCharactersUseCase: GetMarvelCharactersUseCase,
    private val getMarvelCharactersByIdUseCase: GetMarvelCharactersByIdUseCase
) : ViewModel() {

    val charactersListModel = MutableLiveData<List<MarvelCharacter>>()
    val isLoading = MutableLiveData<Boolean>()
    val characterModel = MutableLiveData<MarvelCharacter>()

    fun getAllMarvelCharacters() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getMarvelCharactersUseCase()

            if (!result.isNullOrEmpty()) {
                charactersListModel.postValue(result)
            }
            isLoading.postValue(false)
        }
    }

    fun getCharacterById(id:Int){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getMarvelCharactersByIdUseCase.invoke(id)
            characterModel.postValue(result.first())
            isLoading.postValue(false)
        }
    }


}