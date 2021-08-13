package com.example.projetovicintegrador.data.remote.model

import com.google.gson.annotations.SerializedName

data class ItemCastingResponse(
    @SerializedName("known_for_department") val knownForDepartament: String,
    @SerializedName("original_name") val originalName: String,
    @SerializedName("profile_path") val profilePath: String,
    )
