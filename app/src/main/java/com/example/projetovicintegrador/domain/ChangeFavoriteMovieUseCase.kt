package com.example.projetovicintegrador.domain

import com.example.projetovicintegrador.IMovieRepository
import com.example.projetovicintegrador.model.Filme
import com.example.projetovicintegrador.model.GenreReference
import com.example.projetovicintegrador.model.Resource
import javax.inject.Inject

class ChangeFavoriteMovieUseCase @Inject constructor(private val repository: IMovieRepository) {
    suspend fun execute(movie: Filme): Resource<Exception, Unit> {
        return repository.changeFavoriteMovie(movie)
    }
}