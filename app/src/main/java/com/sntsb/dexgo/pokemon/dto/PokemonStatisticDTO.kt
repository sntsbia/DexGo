package com.sntsb.dexgo.pokemon.dto

import com.sntsb.dexgo.type.dto.TypeDTO

data class PokemonStatisticDTO(
    val id: Int,
    val name: String,
    val image: List<ImageDTO>,
    val height: Int,
    val weight: Int,
    val typeList: List<TypeDTO>,
    val status: List<StatisticDTO>
) {
    override fun toString(): String {
        return "PokemonStatisticDTO(id=$id, name='$name', image=$image, height=$height, weight=$weight, typeList=$typeList, status=$status)"
    }
}