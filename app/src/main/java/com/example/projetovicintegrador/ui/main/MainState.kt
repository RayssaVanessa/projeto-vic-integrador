package com.example.projetovicintegrador.ui.main

import com.example.projetovicintegrador.model.GenreReference
import com.example.projetovicintegrador.model.MovieReference

// oque pode vir da view model (contrato)
sealed class MainState{
    class LoadMovies(val movies: List<MovieReference>)

    class LoadGenres(val genres: List<GenreReference>)
}
