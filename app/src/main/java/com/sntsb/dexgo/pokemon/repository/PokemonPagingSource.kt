package com.sntsb.dexgo.pokemon.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sntsb.dexgo.pokemon.api.PokemonAPI
import com.sntsb.dexgo.pokemon.dto.PokemonDTO

class PokemonPagingSource(private val pokemonApi: PokemonAPI) : PagingSource<Int, PokemonDTO>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonDTO> {
        try {

            Log.e(TAG, "load: ${params}")

            val pageNumber = params.key ?: 0
            val offset = pageNumber * params.loadSize

            val response = pokemonApi.getPokemonList(params.loadSize, offset)
            Log.e(TAG, "load: ${response.results.size}")
            Log.e(TAG, "load: ${response.results}")

            val pokemonList = response.results.mapIndexed { index, pokemon ->
                val id = offset + index + 1
                val imageUrl = getPokemonImageUrl(id) // Função para obter a URL da imagem
                PokemonDTO(
                    id, pokemon.name, imageUrl
                )
            }

            Log.e(TAG, "PokemonList: $pokemonList")

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

    private fun getPokemonImageUrl(id: Int): String {

        return String.format(java.util.Locale.getDefault(), URL_IMAGEM_DEFAULT, id)
    }

    private fun getPokemonShinyImageUrl(id: Int): String {
        return String.format(java.util.Locale.getDefault(), URL_IMAGEM_SHINY, id)
    }

    private fun getPokemonTypeImageUrl(id: Int): String {
        return String.format(java.util.Locale.getDefault(), URL_IMAGEM_TIPO, id)
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonDTO>): Int? {
        return state.anchorPosition
    }

    companion object {
        private const val TAG = "PokemonPagingSource"

        val URL_IMAGEM_DEFAULT =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/%d.png"

        val URL_IMAGEM_SHINY =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/%d.png"

        val URL_IMAGEM_TIPO =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-viii/sword-shield/%d.png"

    }
}