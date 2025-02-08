package com.sntsb.dexgo.pokemon.dto

data class StatisticDTO(
    val description: String, val value: Int
) {
    override fun toString(): String {
        return "StatisticDTO(description='$description', value=$value)"
    }
}