package com.sntsb.dexgo.pokemon.api.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<StatisticResponse>
)