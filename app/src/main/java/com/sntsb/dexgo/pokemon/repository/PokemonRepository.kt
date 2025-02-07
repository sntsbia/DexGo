package com.sntsb.dexgo.pokemon.repository

import com.sntsb.dexgo.pokemon.api.PokemonAPI
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val pokemonApi: PokemonAPI) {

    fun getPokemonList() = "Chamou PokemonRepository"

}