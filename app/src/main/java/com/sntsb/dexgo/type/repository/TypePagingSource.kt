package com.sntsb.dexgo.type.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sntsb.dexgo.pokemon.api.PokemonAPI
import com.sntsb.dexgo.pokemon.dto.PokemonDTO
import com.sntsb.dexgo.utils.PokemonUtils

class TypePagingSource(private val pokemonApi: PokemonAPI, private val query: String = "") :
    PagingSource<Int, PokemonDTO>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonDTO> {
        try {

            Log.e(TAG, "load: $params")

            val pageNumber = params.key ?: 0
            val offset = pageNumber * params.loadSize

            val response = pokemonApi.getPokemonList(params.loadSize, offset)
            Log.e(TAG, "load: ${response.results.size}")

            val pokemonList = response.results.mapIndexed { index, pokemon ->
                val id = offset + index + 1
                val imageUrl =
                    PokemonUtils.getPokemonImageUrl(id) // Função para obter a URL da imagem
                PokemonDTO(
                    id, pokemon.name, imageUrl
                )
            }

            return LoadResult.Page(
                data = pokemonList,
                prevKey = if (pageNumber == 0) null else pageNumber - 1,
                nextKey = if (response.results.isEmpty()) null else pageNumber + 1
            )
        } catch (e: Exception) {
            Log.e(TAG, "load: Error: ${e.message}")
            e.printStackTrace()
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonDTO>): Int? {
        return state.anchorPosition
    }

    companion object {
        private const val TAG = "PokemonPagingSource"

    }
}