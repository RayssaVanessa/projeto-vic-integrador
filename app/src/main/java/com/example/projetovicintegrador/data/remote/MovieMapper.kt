package com.example.projetovicintegrador.data.remote

import com.example.projetovicintegrador.data.remote.model.GenresReferenceResponse
import com.example.projetovicintegrador.data.remote.model.ItemMovieResponse
import com.example.projetovicintegrador.data.remote.model.MoviesReferenceResponse
import com.example.projetovicintegrador.model.GenreReference
import com.example.projetovicintegrador.model.MovieReference

object MovieMapper {
    private const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w185"

    //Funçao de transformar o obj da API no que preciso na View
    fun movieResponseToMovieReference(response: MoviesReferenceResponse): List<MovieReference> {
        return response.results.map { itemMovieToMovieReference(it) }
    }

    fun itemMovieToMovieReference(response: ItemMovieResponse): MovieReference {
        return MovieReference(BASE_URL_IMAGE + response.posterPath,
            response.id,
            response.title,
            (response.voteAverage * 10).toString() + "%",
            response.genreIds
        )
    }

    fun itemMovieToMovieReference(response: List<ItemMovieResponse>): List<MovieReference> {
        // Transforando a lista
        return response.map { itemMovieToMovieReference(it) }

    }

    fun genreResponseToGenreReference(response: GenresReferenceResponse): List<GenreReference> {
        return response.genres.map { GenreReference(it.id, it.nameGenre) }
    }
}