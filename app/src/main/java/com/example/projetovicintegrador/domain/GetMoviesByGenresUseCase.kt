package com.example.projetovicintegrador.domain

import com.example.projetovicintegrador.IMovieRepository
import com.example.projetovicintegrador.model.GenreReference
import com.example.projetovicintegrador.model.MovieReference
import com.example.projetovicintegrador.model.Resource
import javax.inject.Inject

class GetMoviesByGenresUseCase @Inject constructor(private val repository: IMovieRepository) {
    suspend fun execute(ids: List<Int>): Resource<Exception, List<MovieReference>> {
        return repository.getMoviesByGenres(ids)
    }
}