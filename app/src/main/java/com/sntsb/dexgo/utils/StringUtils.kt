package com.sntsb.dexgo.utils

class StringUtils {
    companion object {

        fun capitalizeFirstLetter(string: String): String {

            if (string.isEmpty()) {
                return string
            }
            val firstChar = string[0].uppercaseChar()
            val restOfString = string.substring(1)
            return "$firstChar$restOfString"
        }

        fun allLowercase(string: String): String {
            return string.lowercase()
        }
    }
}