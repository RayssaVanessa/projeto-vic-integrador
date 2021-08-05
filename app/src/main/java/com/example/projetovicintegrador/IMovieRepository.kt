package com.example.projetovicintegrador

import com.example.projetovicintegrador.model.Filme
import com.example.projetovicintegrador.model.GenreReference
import com.example.projetovicintegrador.model.MovieReference
import com.example.projetovicintegrador.model.Resource

interface IMovieRepository {
    suspend fun getMovies(): Resource<Exception, List<MovieReference>>
    suspend fun getGenre(): Resource<Exception, List<GenreReference>>
    suspend fun getDetail(id: Long): Resource<Exception, Filme>
    suspend fun getSearchMovies(title: String): Resource<Exception, List<MovieReference>>
    suspend fun getMoviesByGenres(ids: List<Int>): Resource<Exception, List<MovieReference>>
    suspend fun changeFavoriteMovie(id: Long): Resource<Exception, Unit>
}