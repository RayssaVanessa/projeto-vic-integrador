package com.example.projetovicintegrador

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projetovicintegrador.domain.GetMoviesUseCase
import com.example.projetovicintegrador.model.Resource
import kotlinx.coroutines.launch


class MainViewModel @ViewModelInject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
) : ViewModel() {

    private val _state = MutableLiveData<Any>()
    val state get() = _state

    fun getMovies() {
        viewModelScope.launch {
            when (val result = getMoviesUseCase.execute()) {
                is Resource.Value -> {
                    _state.postValue(MainState.LoadMovies(result.value))
                }
                is Resource.Error -> {
                    _state.postValue(result.error!!)
                }
            }
        }
    }
}