package com.sntsb.dexgo.pokemon.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.sntsb.dexgo.pokemon.api.PokemonAPI
import com.sntsb.dexgo.pokemon.dto.PokemonDTO
import com.sntsb.dexgo.pokemon.model.PokemonModel
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val pokemonApi: PokemonAPI) {

    fun getPokemonList() = "Chamou PokemonRepository"

    fun restart(): Pager<Int, PokemonDTO> {
        return Pager(config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { PokemonPagingSource(pokemonApi) })
    }

    fun getPokemonPager(): Pager<Int, PokemonDTO> {
        return Pager(config = PagingConfig(pageSize = PokemonAPI.LIMIT, enablePlaceholders = false),
            pagingSourceFactory = { PokemonPagingSource(pokemonApi) })
    }

    suspend fun getPokemon(id: Int): PokemonModel? {

        return null
    }

}