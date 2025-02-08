package com.sntsb.dexgo.pokemon.dto

import com.sntsb.dexgo.type.dto.TypeDTO

data class PokemonDTO(
    val id: Int, val name: String, val image: String, val typeList: List<TypeDTO>
) {
    override fun toString(): String {
        return "PokemonDTO(id=$id, name='$name', image='$image')"
    }
}