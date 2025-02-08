package com.sntsb.dexgo.pokemon.api.model

import com.google.gson.annotations.SerializedName

data class PokemonStatisticResponse(
    @SerializedName("height") val height: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("weight") val weight: Int,
    @SerializedName("stats") val statisticList: List<StatusResponse>,
    @SerializedName("types") val typeList: List<PokemonTypeResponse>,
)