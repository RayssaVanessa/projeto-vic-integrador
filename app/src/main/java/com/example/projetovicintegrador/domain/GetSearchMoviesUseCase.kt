package com.example.projetovicintegrador.domain

import com.example.projetovicintegrador.IMovieRepository
import com.example.projetovicintegrador.model.GenreReference
import com.example.projetovicintegrador.model.MovieReference
import com.example.projetovicintegrador.model.Resource
import javax.inject.Inject

class GetSearchMoviesUseCase @Inject constructor(private val repository: IMovieRepository) {
    suspend fun execute(title: String): Resource<Exception, List<MovieReference>> {
        return repository.getSearchMovies(title)
    }
}