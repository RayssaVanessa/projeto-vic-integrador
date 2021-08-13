package com.example.projetovicintegrador.data.remote.model

import com.google.gson.annotations.SerializedName

data class PgItemResponse(
    @SerializedName("iso_3166_1") val iso: String,
    @SerializedName("release_dates") val releaseDates: List<CertificationResponse>
)
