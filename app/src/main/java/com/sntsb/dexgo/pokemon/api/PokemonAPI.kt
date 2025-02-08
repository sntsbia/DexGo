package com.sntsb.dexgo.pokemon.api

import com.sntsb.dexgo.pokemon.api.model.PokemonResponse
import com.sntsb.dexgo.pokemon.api.model.PokemonStatisticResponse
import com.sntsb.dexgo.type.api.model.TypeResponse
import com.sntsb.dexgo.type.api.model.TypeStatisticResponse
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

    @GET("type/{id}")
    suspend fun getTypeById(@Path("id") id: String): TypeStatisticResponse?

    @GET("type")
    suspend fun getTypes(): TypeResponse

    companion object {
        const val LIMIT = 20
    }

}