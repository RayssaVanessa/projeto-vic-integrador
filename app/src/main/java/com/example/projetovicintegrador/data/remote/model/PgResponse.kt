package com.example.projetovicintegrador.data.remote.model

import com.google.gson.annotations.SerializedName

data class PgResponse(
    @SerializedName("results") val results: List<PgItemResponse>
)
