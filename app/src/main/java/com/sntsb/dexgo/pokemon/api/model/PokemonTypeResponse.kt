package com.sntsb.dexgo.pokemon.api.model

import com.google.gson.annotations.SerializedName

data class PokemonTypeResponse(
    @SerializedName("pokemon") val pokemon: StatisticResponse
)