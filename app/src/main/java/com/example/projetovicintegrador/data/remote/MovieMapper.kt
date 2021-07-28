package com.example.projetovicintegrador.data.remote

import com.example.projetovicintegrador.data.remote.model.ItemMovieResponse
import com.example.projetovicintegrador.data.remote.model.MoviesReferenceResponse
import com.example.projetovicintegrador.model.MovieReference

object MovieMapper {
    //Funçao de transformar o obj da API no que preciso na View
    fun movieResponseToMovieReference(response: MoviesReferenceResponse): List<MovieReference> {
        return response.results.map { itemMovieToMovieReference(it) }
    }

    fun itemMovieToMovieReference(response: ItemMovieResponse): MovieReference {
        return MovieReference(response.posterPath,
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
}