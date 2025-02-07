package com.sntsb.dexgo.pokemon.dto

data class PokemonDTO(
    val id: Int, val name: String, val image: String
) {
    override fun toString(): String {
        return "PokemonDTO(id=$id, name='$name', image='$image')"
    }
}