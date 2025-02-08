package com.sntsb.dexgo.pokemon.dto

data class ImageDTO(
    val name: String, val url: String
) {

    companion object {
        const val FRONT_IMAGE = "front"
        const val SHINY_IMAGE = "shiny"
    }

    override fun toString(): String {
        return "ImageDTO(name='$name', url='$url')"
    }
}