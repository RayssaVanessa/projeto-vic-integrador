package com.example.projetovicintegrador.data.remote.api

import com.example.projetovicintegrador.data.remote.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    //camada de comunicaçõa comm API
    @GET("3/movie/popular")
    suspend fun getMovies(
        @Query("api_key") api: String = "50a01967a2adac9736c537bc3ac4bcd5",
        @Query("language") language: String = "pt-BR",
        @Query("region") region: String = "BR",
    ): MoviesReferenceResponse

    @GET("3/genre/movie/list")
    suspend fun getListGenre(
        @Query("api_key") api: String = "50a01967a2adac9736c537bc3ac4bcd5",
        @Query("language") language: String = "pt-BR",
    ): GenresReferenceResponse

    @GET("3/movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") id: Long,
        @Query("api_key") api: String = "50a01967a2adac9736c537bc3ac4bcd5",
        @Query("language") language: String = "pt-BR",
    ): DetailMovieReferenceResponse

    @GET("3/movie/{movie_id}/credits")
    suspend fun getCast(
        @Path("movie_id") id: Long,
        @Query("api_key") api: String = "50a01967a2adac9736c537bc3ac4bcd5",
        @Query("language") language: String = "pt-BR",
    ): CastReferenceResponse

    @GET("3/discover/movie")
    suspend fun getMovieWithGenre(
        @Query("with_genres") genres: List<Int>,
        @Query("api_key") api: String = "50a01967a2adac9736c537bc3ac4bcd5",
        @Query("language") language: String = "pt-BR",
    ): SearchMoviesReferenceResponse

    @GET("3/search/movie")
    suspend fun getSearchMovie(
        @Query("query") title: String,
        @Query("api_key") api: String = "50a01967a2adac9736c537bc3ac4bcd5",
        @Query("language") language: String = "pt-BR",
    ): SearchMoviesReferenceResponse

    @GET("3/movie/{movie_id}/release_dates")
    suspend fun getPgMovies(
        @Path("movie_id") movieId: Long,
        @Query("api_key") api: String = "50a01967a2adac9736c537bc3ac4bcd5"
    ) :PgResponse
}
