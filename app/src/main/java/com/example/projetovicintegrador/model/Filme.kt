package com.example.projetovicintegrador.model

data class Filme(
    val id: Long,
    val nameFilm: String,
    val rate: String,
    val title: String,
    val poster: String,
    val listElenco: List<Elenco>,
    val favorite: Boolean,
    val genre: List<String>,
    val year: String,
    val biography: String,
    val synopsis: String,
    val time: String,
    val pg: String,
)