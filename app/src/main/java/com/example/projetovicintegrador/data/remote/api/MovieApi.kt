package com.example.projetovicintegrador.data.remote.api

import com.example.projetovicintegrador.data.remote.model.MoviesReferenceResponse
import retrofit2.http.GET

interface MovieApi {
//camada de comunicaçõa comm API
    @GET("/movie/popular")
    suspend fun getMovies() : MoviesReferenceResponse
}