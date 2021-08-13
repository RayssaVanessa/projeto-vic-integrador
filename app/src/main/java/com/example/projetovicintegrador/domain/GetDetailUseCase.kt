package com.example.projetovicintegrador.domain

import com.example.projetovicintegrador.IMovieRepository
import com.example.projetovicintegrador.model.Filme
import com.example.projetovicintegrador.model.GenreReference
import com.example.projetovicintegrador.model.Resource
import javax.inject.Inject

class GetDetailUseCase @Inject constructor(private val repository: IMovieRepository) {
    suspend fun execute(id: Long): Resource<Exception, Filme> {
        return repository.getDetail(id)
    }
}