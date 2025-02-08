package com.sntsb.dexgo.type.api.model

import com.google.gson.annotations.SerializedName
import com.sntsb.dexgo.pokemon.api.model.StatisticResponse

class TypePokemonResponse(
    @SerializedName("pokemon") val pokemon: StatisticResponse
)