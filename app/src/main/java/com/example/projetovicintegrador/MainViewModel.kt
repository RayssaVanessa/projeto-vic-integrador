package com.example.projetovicintegrador

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projetovicintegrador.domain.GetGenreUseCase
import com.example.projetovicintegrador.domain.GetMoviesUseCase
import com.example.projetovicintegrador.model.Resource
import kotlinx.coroutines.launch


class MainViewModel @ViewModelInject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getGenreUseCase: GetGenreUseCase,
) : ViewModel() {

    private val _state = MutableLiveData<Any>()
    val state get() = _state

    fun getMovies() {
        //execucao síncrona
        viewModelScope.launch {
            when (val result = getMoviesUseCase.execute()) {
                is Resource.Value -> {
                    _state.value = MainState.LoadMovies(result.value)
                }
                is Resource.Error -> {
                    _state.value = result.error!!
                    //enviando p activity a lista se for sucesso e o erro se der erro
                }
            }
        }
    }

    fun getGenres() {
        //execucao sincroca
        viewModelScope.launch {
            when (val result = getGenreUseCase.execute()) {
                is Resource.Value -> {
                    _state.value = MainState.LoadGenres(result.value)
                }
                is Resource.Error -> {
                    _state.value = result.error!!
                }
            }
        }
    }
}