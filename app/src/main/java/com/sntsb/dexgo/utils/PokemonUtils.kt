package com.sntsb.dexgo.utils

class PokemonUtils {
    companion object {
        private const val URL_IMAGEM_DEFAULT =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/%d.png"

        private const val URL_IMAGEM_SHINY =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/%d.png"

        private const val URL_IMAGEM_TIPO =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-viii/sword-shield/%d.png"

        fun getPokemonImageUrl(id: Int): String {
            return String.format(
                java.util.Locale.getDefault(), URL_IMAGEM_DEFAULT, id
            )
        }

        fun getPokemonShinyImageUrl(id: Int): String {
            return String.format(
                java.util.Locale.getDefault(), URL_IMAGEM_SHINY, id
            )
        }

        fun getPokemonTypeImageUrl(id: Int): String {
            return String.format(
                java.util.Locale.getDefault(), URL_IMAGEM_TIPO, id
            )
        }

    }

}