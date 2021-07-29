package com.example.projetovicintegrador.data.remote

import com.example.projetovicintegrador.data.remote.api.MovieApi
import com.example.projetovicintegrador.data.remote.model.GenresReferenceResponse
import com.example.projetovicintegrador.data.remote.model.MoviesReferenceResponse

class MovieRemote(
    private val api: MovieApi
) {
    suspend fun getMovies(): MoviesReferenceResponse {
        return api.getMovies()
    }

    suspend fun getGenres(): GenresReferenceResponse {
        return api.getListGenre()
    }
}