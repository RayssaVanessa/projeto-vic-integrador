package com.example.projetovicintegrador.data.remote.api

import com.example.projetovicintegrador.data.remote.model.MoviesReferenceResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    //camada de comunicaçõa comm API
    @GET("/movie/popular")
    suspend fun getMovies(
        @Query("api_key") api: String = "50a01967a2adac9736c537bc3ac4bcd5",
        @Query("language") language: String = "pt-BR",
        @Query("region") region: String = "BR"
    ): MoviesReferenceResponse
}