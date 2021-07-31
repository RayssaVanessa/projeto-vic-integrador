package com.example.projetovicintegrador.data.remote.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class DetailMovieReferenceResponse(
    val genres: List<ItemGenreResponse>,
    val title: String,
    @SerializedName("vote_average") val voteAverage: Float,
    val runtime: Int,
    @SerializedName("release_date") val releaseDate: Date,
    @SerializedName("backdrop_path")  val posterPath: String,
    val overview: String,
    val id: Long
)
