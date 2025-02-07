package com.sntsb.dexgo.pokemon.api.model

import android.net.http.UrlRequest.Status
import com.google.gson.annotations.SerializedName

data class PokemonStatisticResponse(
    @SerializedName("height") val height: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("weight") val weight: Int,
    @SerializedName("stats") val statisticList: List<Status>,
    @SerializedName("types") val typeList: List<StatisticResponse>,
)