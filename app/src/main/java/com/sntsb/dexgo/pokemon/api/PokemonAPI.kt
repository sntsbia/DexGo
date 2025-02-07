package com.sntsb.dexgo.pokemon.api

import com.sntsb.dexgo.pokemon.model.PokemonDetailModel
import com.sntsb.dexgo.pokemon.model.PokemonImageModel
import com.sntsb.dexgo.pokemon.model.PokemonModel
import com.sntsb.dexgo.type.model.TypeModel
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonAPI {

    @GET("pokemon")
    suspend fun getPokemonList(): List<PokemonModel>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(@Path("id") id: Int): PokemonDetailModel

    @GET("pokemon/{id}/")
    suspend fun getPokemonImages(@Path("id") id: Int): List<PokemonImageModel>

    @GET("type")
    suspend fun getTypes(): List<TypeModel>

    @GET("pokemon?limit={limit}&offset={offset}}")
    suspend fun getPokemonsPaginated(
        @Path("limit") limit: Int = LIMIT,
        @Path("offset") offset: Int
    ): List<PokemonModel>

    companion object {
        const val LIMIT = 20
    }

}