package com.sntsb.dexgo.type.api.model

import com.google.gson.annotations.SerializedName
import com.sntsb.dexgo.pokemon.api.model.StatisticResponse

class TypeResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<StatisticResponse>
)