package com.example.projetovicintegrador.domain

import com.example.projetovicintegrador.IMovieRepository
import com.example.projetovicintegrador.model.GenreReference
import com.example.projetovicintegrador.model.Resource
import javax.inject.Inject

class GetGenreUseCase @Inject constructor(private val repository: IMovieRepository) {
    suspend fun execute(): Resource<Exception, List<GenreReference>> {
        return repository.getGenre()
    }
}