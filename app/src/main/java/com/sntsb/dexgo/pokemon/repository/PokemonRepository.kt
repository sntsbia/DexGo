package com.sntsb.dexgo.pokemon.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.sntsb.dexgo.pokemon.api.PokemonAPI
import com.sntsb.dexgo.pokemon.dto.PokemonDTO
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val pokemonApi: PokemonAPI) {

    fun restart(): Pager<Int, PokemonDTO> {
        return Pager(config = PagingConfig(pageSize = PokemonAPI.LIMIT, enablePlaceholders = false),
            pagingSourceFactory = { PokemonPagingSource(pokemonApi) })
    }

    fun getPokemonPager(query: String): Pager<Int, PokemonDTO> {
        return Pager(config = PagingConfig(pageSize = PokemonAPI.LIMIT, enablePlaceholders = false),
            pagingSourceFactory = { PokemonPagingSource(pokemonApi, query) })
    }

    companion object

}