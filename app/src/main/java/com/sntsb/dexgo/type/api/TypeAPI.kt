package com.sntsb.dexgo.type.api

import com.sntsb.dexgo.type.api.model.TypeResponse
import com.sntsb.dexgo.type.api.model.TypeStatisticResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TypeAPI {
    @GET("type/{id}")
    suspend fun getTypeById(@Query("id") id: Int): TypeStatisticResponse

    @GET("type")
    suspend fun getTipos(): TypeResponse

    companion object {
        const val LIMIT = 20
    }
}