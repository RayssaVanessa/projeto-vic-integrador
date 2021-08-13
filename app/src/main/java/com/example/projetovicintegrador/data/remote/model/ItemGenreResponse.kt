package com.example.projetovicintegrador.data.remote.model

import com.google.gson.annotations.SerializedName

data class ItemGenreResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val nameGenre: String
)
