package com.example.projetovicintegrador.data.remote.model

import com.google.gson.annotations.SerializedName

data class CertificationResponse(
    @SerializedName("certification") val certification: String,
)
