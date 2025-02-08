package com.sntsb.dexgo.type.api

import com.sntsb.dexgo.type.api.model.TypeResponse
import com.sntsb.dexgo.type.api.model.TypeStatisticResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TypeAPI {

    @GET("type/{id}")
    suspend fun getTypeById(@Path("id") id: String): TypeStatisticResponse?

    @GET("type")
    suspend fun getTypes(): TypeResponse

    companion object {
        const val LIMIT = 20
    }
}