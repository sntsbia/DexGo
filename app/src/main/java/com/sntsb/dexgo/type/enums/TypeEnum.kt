package com.sntsb.dexgo.type.enums

enum class TypeEnum {
    normal, fighting, flying, poison, ground, rock, bug, ghost, steel, fire, water, grass, electric, psychic, ice, dragon, dark, fairy, UNKNOWN;

    companion object {
        fun from(value: String): TypeEnum {
            return entries.find { it.name.equals(value, ignoreCase = true) } ?: UNKNOWN
        }
    }
}