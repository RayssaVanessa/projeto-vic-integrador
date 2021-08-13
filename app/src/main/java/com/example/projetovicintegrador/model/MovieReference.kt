package com.example.projetovicintegrador.model

data class MovieReference(
    val posterPath: String,
    val id: Long,
    val title: String,
    val voteAverage: String,
    val genreIds: List<Int>,
    var isFavorite: Boolean = false
    )
