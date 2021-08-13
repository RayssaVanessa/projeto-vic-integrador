package com.example.projetovicintegrador.domain

import com.example.projetovicintegrador.IMovieRepository
import com.example.projetovicintegrador.model.Filme
import com.example.projetovicintegrador.model.GenreReference
import com.example.projetovicintegrador.model.MovieReference
import com.example.projetovicintegrador.model.Resource
import javax.inject.Inject

class ChangeFavoriteReferenceMovieUseCase @Inject constructor(private val repository: IMovieRepository) {
    suspend fun execute(movie: MovieReference): Resource<Exception, Unit> {
        return repository.changeFavoriteReferenceMovie(movie)
    }
}