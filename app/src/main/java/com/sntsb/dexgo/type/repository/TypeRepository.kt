package com.sntsb.dexgo.type.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.sntsb.dexgo.paging.model.PagingParams
import com.sntsb.dexgo.pokemon.api.PokemonAPI
import com.sntsb.dexgo.pokemon.dto.ImageDTO
import com.sntsb.dexgo.pokemon.dto.PokemonDTO
import com.sntsb.dexgo.pokemon.dto.PokemonStatisticDTO
import com.sntsb.dexgo.pokemon.dto.StatisticDTO
import com.sntsb.dexgo.pokemon.repository.PokemonPagingSource
import com.sntsb.dexgo.type.dto.TypeDTO
import com.sntsb.dexgo.utils.PokemonUtils
import javax.inject.Inject

class TypeRepository @Inject constructor(private val pokemonApi: PokemonAPI) {

    val pagingSource = fun(query: String): Pager<Int, PokemonDTO> {

        return Pager(config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {
                PokemonPagingSource(pokemonApi, PagingParams(query))
            })

    }

    suspend fun getOne(id: String): PokemonStatisticDTO? {
        try {

            val response = pokemonApi.getPokemonById(id)

            return response?.let { pokemon ->

                Log.e(TAG, "getOne: ${pokemon}")

                val status = pokemon.statisticList.map { stat ->
                    StatisticDTO(stat.stat.name, stat.valorBase)
                }

                val tipos = pokemon.typeList.map { type ->

                    val idTipo = type.type.url.split("/").let { it[it.size - 2] }
                    val imagem = PokemonUtils.getPokemonTypeImageUrl(idTipo.toIntOrNull() ?: -1)
                    TypeDTO(
                        idTipo.toIntOrNull() ?: -1, type.type.name, imagem
                    )
                }

                val imagemArray = ArrayList<ImageDTO>()
                imagemArray.add(
                    ImageDTO(
                        ImageDTO.IMAGEM_FRONT, PokemonUtils.getPokemonImageUrl(pokemon.id)
                    )
                )
                imagemArray.add(
                    ImageDTO(
                        ImageDTO.IMAGEM_SHINY, PokemonUtils.getPokemonShinyImageUrl(pokemon.id)
                    )
                )

                PokemonStatisticDTO(
                    pokemon.id,
                    pokemon.name,
                    imagemArray,
                    pokemon.height,
                    pokemon.weight,
                    tipos,
                    status
                )
            }

        } catch (e: Exception) {
            Log.e(TAG, "getOne: Error: ${e.message}")
            e.printStackTrace()
            return null

        }

    }

    companion object {
        private const val TAG = "PokemonRepository"

    }

}