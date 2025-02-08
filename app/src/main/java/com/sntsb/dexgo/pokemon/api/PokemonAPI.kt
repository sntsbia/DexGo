package com.sntsb.dexgo.pokemon.api

import com.sntsb.dexgo.pokemon.api.model.PokemonResponse
import com.sntsb.dexgo.pokemon.api.model.PokemonStatisticResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonAPI {

    @GET("pokemon")
    suspend fun getPokemonList(): PokemonResponse

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = LIMIT, @Query("offset") offset: Int = 0
    ): PokemonResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: String): PokemonStatisticResponse?

    companion object {
        const val LIMIT = 20
    }

}