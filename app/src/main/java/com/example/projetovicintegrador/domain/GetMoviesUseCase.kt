package com.example.projetovicintegrador.domain

import com.example.projetovicintegrador.IMovieRepository
import com.example.projetovicintegrador.model.MovieReference
import com.example.projetovicintegrador.model.Resource
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: IMovieRepository) {
    suspend fun execute(): Resource<Exception, List<MovieReference>> {
        return repository.getMovies()
    }
}