package com.example.projetovicintegrador.ui.detail

import com.example.projetovicintegrador.model.Filme
import com.example.projetovicintegrador.model.GenreReference
import com.example.projetovicintegrador.model.MovieReference

// oque pode vir da view model (contrato)
sealed class DetailState{
    class LoadDetail(val detail: Filme)

}
