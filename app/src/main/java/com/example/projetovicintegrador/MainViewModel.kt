package com.example.projetovicintegrador

import androidx.core.view.isVisible
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projetovicintegrador.domain.*
import com.example.projetovicintegrador.model.MovieReference
import com.example.projetovicintegrador.model.Resource
import kotlinx.coroutines.launch


class MainViewModel @ViewModelInject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getGenreUseCase: GetGenreUseCase,
    private val getSearchMoviesUseCase: GetSearchMoviesUseCase,
    private val getMoviesByGenresUseCase: GetMoviesByGenresUseCase,
    private val getFavoriteMovieUseCase: GetFavoriteMovieUseCase,
    private val changeFavoriteReferenceMovieUseCase: ChangeFavoriteReferenceMovieUseCase
) : ViewModel() {

    private val _state = MutableLiveData<Any>()
    val state get() = _state

    private var movies = listOf<MovieReference>()

    fun getMovies() {
        //execucao síncrona
        viewModelScope.launch {
            when (val result = getMoviesUseCase.execute()) {
                is Resource.Value -> {
                    movies = result.value //resposta da api
                    _state.value = MainState.LoadMovies(movies)
                }
                is Resource.Error -> {
                    _state.value = result.error!!
                    //enviando p activity a lista se for sucesso e o erro se der erro
                }
            }
        }
    }

    fun getSearchMovies(title: String) {
        viewModelScope.launch {
            when (val result = getSearchMoviesUseCase.execute(title)) {
                is Resource.Value -> {
                    movies = result.value
                    _state.value = MainState.LoadMovies(movies)
                }
                is Resource.Error -> {
                    _state.value = result.error!!
                    //enviando p activity a lista se for sucesso e o erro se der erro
                }
            }
        }
    }

    private fun getMoviesByGenres(ids: List<Int>) {
        viewModelScope.launch {
            when (val result = getMoviesByGenresUseCase.execute(ids)) {
                is Resource.Value -> {
                    movies = result.value
                    _state.value = MainState.LoadMovies(movies)
                }
                is Resource.Error -> {
                    _state.value = result.error!!
                    //enviando p activity a lista se for sucesso e o erro se der erro
                }
            }
        }
    }

    fun getGenres() {
        //execucao sincrona
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

    fun filterByGenres(genres: List<Int>, isLocalSearch: Boolean) {
        if (isLocalSearch) {
            _state.value = MainState.LoadMovies(movies.filter { it.genreIds.containsAll(genres) })
        } else {
            getMoviesByGenres(genres)
        }
    }

    fun getFavoriteMovie() {
        //execucao síncrona
        viewModelScope.launch {
            when (val result = getFavoriteMovieUseCase.execute()) {
                is Resource.Value -> {
                    _state.value = MainState.LoadMovies(result.value)
                }
                is Resource.Error -> {
                    _state.value = result.error!!
                }
            }
        }
    }

    fun changeFavoriteMovie(movieReference: MovieReference) {
        viewModelScope.launch {
            when (val result = changeFavoriteReferenceMovieUseCase.execute(movieReference)) {
                is Resource.Value -> {

                }
                is Resource.Error -> {
                    _state.value = result.error!!
                }
            }
        }
    }
}