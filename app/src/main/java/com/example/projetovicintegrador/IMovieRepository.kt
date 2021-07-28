package com.example.projetovicintegrador

import com.example.projetovicintegrador.model.MovieReference
import com.example.projetovicintegrador.model.Resource

interface IMovieRepository {
    suspend fun getMovies(): Resource<Exception, List<MovieReference>>
}