package com.sntsb.dexgo.type.api.model

import com.google.gson.annotations.SerializedName

data class TypeStatisticResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("pokemon") val pokemon: List<TypePokemonResponse>
)
