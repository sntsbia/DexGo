package com.sntsb.dexgo.pokemon.dto

data class ImageDTO(
    val name: String, val url: String
) {

    companion object {
        const val IMAGEM_FRONT = "front"
        const val IMAGEM_SHINY = "shiny"

    }

    override fun toString(): String {
        return "ImageDTO(name='$name', url='$url')"
    }
}