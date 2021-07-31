package com.example.projetovicintegrador

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projetovicintegrador.domain.GetDetailUseCase
import com.example.projetovicintegrador.domain.GetGenreUseCase
import com.example.projetovicintegrador.domain.GetMoviesUseCase
import com.example.projetovicintegrador.model.Resource
import kotlinx.coroutines.launch

class DetailViewModel @ViewModelInject constructor(
    private val getDetailsUseCase: GetDetailUseCase,
) : ViewModel() {

    private val _state = MutableLiveData<Any>()
    val state get() = _state

    fun getDetail(id: Long) {
        viewModelScope.launch {
            when (val result = getDetailsUseCase.execute(id)) {
                is Resource.Value -> {
                    _state.value = DetailState.LoadDetail(result.value)
                }
                is Resource.Error -> {
                    _state.value = result.error!!
                }
            }
        }
    }
}