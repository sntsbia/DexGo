package com.sntsb.dexgo.pokemon.api

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonAPI {

    @GET("pokemon")
    suspend fun getPokemonList(): PokemonResponse

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = LIMIT, @Query("offset") offset: Int = 0
    ): PokemonResponse

    @GET("pokemon")
    suspend fun getPokemonById(@Query("id") id: Int): PokemonDetailResponse

    @GET("type")
    suspend fun getTypes(): CategoryResponse

    companion object {
        const val LIMIT = 20
    }

    data class Specification(
        @SerializedName("name") val name: String, @SerializedName("url") val url: String
    )

    data class PokemonResponse(
        @SerializedName("count") val count: Int,
        @SerializedName("next") val next: String?,
        @SerializedName("previous") val previous: String?,
        @SerializedName("results") val results: List<Specification>
    )

    data class CategoryResponse(
        @SerializedName("count") val count: Int,
        @SerializedName("next") val next: String?,
        @SerializedName("previous") val previous: String?,
        @SerializedName("results") val results: List<Category>
    )

    data class PokemonDetailResponse(
        @SerializedName("height") val height: Int,
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("weight") val weight: Int,
        @SerializedName("stats") val statisticList: List<Statistic>,
        @SerializedName("types") val typeList: List<Specification>,
    )

    data class Statistic(
        @SerializedName("base_stat") val baseStatistic: Int,
        @SerializedName("effort") val effort: Int,
        @SerializedName("stat") val statistic: Specification

    )

    data class Category(
        @SerializedName("slot") val slot: String,
        @SerializedName("type") val typeList: List<Specification>
    )

}