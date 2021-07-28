package com.example.projetovicintegrador

import com.example.projetovicintegrador.model.MovieReference

// oque pode vir da view model (contrato)
sealed class MainState{
    class LoadMovies(val movies: List<MovieReference>)
}
