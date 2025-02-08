package com.sntsb.dexgo.utils

class PokemonUtils {
    companion object {
        private const val URL_DEFAULT_IMAGE =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/%d.png"

        private const val URL_SHINY_IMAGE =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/%d.png"

        private const val URL_TYPE_IMAGE =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-viii/sword-shield/%d.png"

        fun getPokemonImageUrl(id: Int): String {
            return if (id >= 0) {
                String.format(
                    java.util.Locale.getDefault(), URL_DEFAULT_IMAGE, id
                )
            } else {
                ""
            }
        }

        fun getPokemonShinyImageUrl(id: Int): String {
            return if (id >= 0) {
                return String.format(
                    java.util.Locale.getDefault(), URL_SHINY_IMAGE, id
                )
            } else {
                ""
            }
        }

        fun getPokemonTypeImageUrl(id: Int): String {
            return if (id >= 0) {
                String.format(
                    java.util.Locale.getDefault(), URL_TYPE_IMAGE, id
                )
            } else {
                ""
            }
        }

    }

}