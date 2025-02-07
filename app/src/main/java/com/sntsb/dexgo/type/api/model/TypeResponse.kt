package com.sntsb.dexgo.type.api.model

import com.google.gson.annotations.SerializedName

class TypeResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<TypePokemonResponse>
)