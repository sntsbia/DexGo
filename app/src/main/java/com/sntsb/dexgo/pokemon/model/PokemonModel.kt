package com.sntsb.dexgo.pokemon.model

import com.sntsb.dexgo.type.model.TypeModel

data class PokemonModel(
    val id: Int,
    val name: String,
    val statisticList: List<PokemonStatisticModel>,
    val typeList: List<TypeModel>
)