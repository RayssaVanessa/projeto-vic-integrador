package com.example.projetovicintegrador.data.remote

import com.example.projetovicintegrador.data.remote.model.*
import com.example.projetovicintegrador.model.Elenco
import com.example.projetovicintegrador.model.Filme
import com.example.projetovicintegrador.model.GenreReference
import com.example.projetovicintegrador.model.MovieReference
import java.text.SimpleDateFormat

object MovieMapper {
    private const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w185"
    private const val BASE_URL_IMAGE_DETAIL = "https://image.tmdb.org/t/p/w300"

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

    fun genreResponseToGenreReference(response: GenresReferenceResponse): List<GenreReference> {
        return response.genres.map { GenreReference(it.id, it.nameGenre) }
    }

    fun createFilme(
        detailResponse: DetailMovieReferenceResponse,
        castResponse: CastReferenceResponse,
    ): Filme {
        return Filme(
            nameFilm = detailResponse.title,
            rate = (detailResponse.voteAverage * 10).toString() + "%",
            title = detailResponse.title,
            poster = BASE_URL_IMAGE_DETAIL + detailResponse.posterPath,
            favorite = false,
            genre = detailResponse.genres.map { it.nameGenre },
            year = SimpleDateFormat("yyyy").format(detailResponse.releaseDate),
            biography = detailResponse.overview,
            synopsis = "",
            time = getDurationTime(detailResponse.runtime),
            pg = "PG",
            listElenco = castResponse.cast.map {
                Elenco(
                    BASE_URL_IMAGE + it.profilePath,
                    it.originalName,
                    it.knownForDepartament
                )
            }
        )
    }

    private fun getDurationTime(time: Int): String {
        return if (time < 60) {
            "${time}m"
        } else {
            "${time / 60}h${time % 60}m"
        }
    }
}