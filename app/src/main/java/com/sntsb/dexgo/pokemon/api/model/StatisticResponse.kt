package com.sntsb.dexgo.pokemon.api.model

import com.google.gson.annotations.SerializedName

data class StatisticResponse(

    @SerializedName("name") val name: String, @SerializedName("url") val url: String

)