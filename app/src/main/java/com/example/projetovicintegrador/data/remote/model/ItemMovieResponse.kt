package com.example.projetovicintegrador.data.remote.model

import com.google.gson.annotations.SerializedName

data class ItemMovieResponse(
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val voteAverage: Float,
    @SerializedName("genre_ids") val genreIds: List<Int>
    )
