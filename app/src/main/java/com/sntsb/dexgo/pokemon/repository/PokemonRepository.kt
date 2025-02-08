package com.sntsb.dexgo.pokemon.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.sntsb.dexgo.pokemon.api.PokemonAPI
import com.sntsb.dexgo.pokemon.dto.ImageDTO
import com.sntsb.dexgo.pokemon.dto.PokemonDTO
import com.sntsb.dexgo.pokemon.dto.PokemonStatisticDTO
import com.sntsb.dexgo.pokemon.dto.StatisticDTO
import com.sntsb.dexgo.type.dto.TypeDTO
import com.sntsb.dexgo.utils.PokemonUtils
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val pokemonApi: PokemonAPI) {

    val pagingSource = fun(query: String): Pager<Int, PokemonDTO> {

        return Pager(config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {
                PokemonPagingSource(pokemonApi).apply {
                    this.query = query
                }
            })

    }

    suspend fun getOne(id: String): PokemonStatisticDTO? {
        try {

            val response = pokemonApi.getPokemonById(id)

            return response?.let { pokemon ->

                Log.e(TAG, "getOne: $pokemon")

                val statisticDTOList = pokemon.statisticList.map { stat ->
                    StatisticDTO(stat.stat.name, stat.valorBase)
                }

                val typeDTOList = pokemon.typeList.map { typePokemon ->

                    val idType = typePokemon.type.url.split("/").let { it[it.size - 2] }
                    val image = PokemonUtils.getPokemonTypeImageUrl(idType.toIntOrNull() ?: -1)
                    TypeDTO(
                        idType.toIntOrNull() ?: -1, typePokemon.type.name, image
                    )
                }

                val imageArray = ArrayList<ImageDTO>()
                imageArray.add(
                    ImageDTO(
                        ImageDTO.IMAGEM_FRONT,
                        PokemonUtils.getPokemonImageUrl(pokemon.id)
                    )
                )
                imageArray.add(
                    ImageDTO(
                        ImageDTO.IMAGEM_SHINY,
                        PokemonUtils.getPokemonShinyImageUrl(pokemon.id)
                    )
                )

                PokemonStatisticDTO(
                    pokemon.id,
                    pokemon.name,
                    imageArray,
                    pokemon.height,
                    pokemon.weight,
                    typeDTOList,
                    statisticDTOList
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