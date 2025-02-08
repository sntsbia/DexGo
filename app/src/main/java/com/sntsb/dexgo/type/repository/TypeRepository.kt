package com.sntsb.dexgo.type.repository

import android.util.Log
import com.sntsb.dexgo.pokemon.api.PokemonAPI
import com.sntsb.dexgo.pokemon.dto.PokemonDTO
import com.sntsb.dexgo.type.dto.TypeDTO
import com.sntsb.dexgo.utils.PokemonUtils
import javax.inject.Inject

class TypeRepository @Inject constructor(
    private val pokemonAPI: PokemonAPI
) {

    suspend fun getTypes(): ArrayList<TypeDTO> {
        val types = ArrayList<TypeDTO>()

        try {

            val response = pokemonAPI.getTypes()

            response.let { resposta ->
                resposta.results.forEach { type ->
                    val id = type.url.split("/").let { it[it.size - 2] }
                    val image = PokemonUtils.getPokemonTypeImageUrl(id.toIntOrNull() ?: -1)
                    types.add(TypeDTO(id.toIntOrNull() ?: -1, type.name, image))
                }
            }

        } catch (e: Exception) {
            Log.e(TAG, "getTypes: Error: ${e.message}")
            e.printStackTrace()
        }

        return types

    }


    suspend fun getByType(id: String): ArrayList<PokemonDTO> {
        try {

            val response = pokemonAPI.getTypeById(id)

            val pokemons = ArrayList<PokemonDTO>()

            response?.let { type ->

                type.pokemon.forEach { pokemonTypeResponse ->

                    val pokemon =
                        pokemonAPI.getPokemonById(pokemonTypeResponse.pokemon.url.split("/")
                            .let { it[it.size - 2] })

                    if (pokemon != null) {

                        val types = pokemon.typeList.map { type ->

                            val idType = type.type.url.split("/").let { it[it.size - 2] }
                            val image =
                                PokemonUtils.getPokemonTypeImageUrl(idType.toIntOrNull() ?: -1)
                            TypeDTO(
                                idType.toIntOrNull() ?: -1, type.type.name, image
                            )
                        }

                        pokemons.add(
                            PokemonDTO(
                                pokemon.id,
                                pokemon.name,
                                PokemonUtils.getPokemonImageUrl(pokemon.id),
                                types
                            )
                        )
                    }
                }
            }

            return pokemons

        } catch (e: Exception) {
            Log.e(TAG, "getOne: Error: ${e.message}")
            e.printStackTrace()
            return arrayListOf()

        }

    }

    companion object {
        private const val TAG = "PokemonRepository"

    }

}