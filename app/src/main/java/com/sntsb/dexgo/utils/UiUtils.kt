package com.sntsb.dexgo.utils

import android.content.Context
import android.util.Log
import com.sntsb.dexgo.R
import com.sntsb.dexgo.type.enums.TypeEnum

class UiUtils(val context: Context) {

    fun getStatusLabel(status: String): String {
        return when (status) {
            "hp" -> context.resources.getString(R.string.hp)
            "attack" -> context.resources.getString(R.string.attack)
            "defense" -> context.resources.getString(R.string.defense)
            "special-attack" -> context.resources.getString(R.string.special_attack)
            "special-defense" -> context.resources.getString(R.string.special_defense)
            "speed" -> context.resources.getString(R.string.speed)
            "total" -> context.resources.getString(R.string.total)
            else -> status
        }

    }

    fun getTypeByLabel(label: String): TypeEnum {
        when (label) {
            context.resources.getString(R.string.normal) -> return TypeEnum.normal
            context.resources.getString(R.string.fighting) -> return TypeEnum.fighting
            context.resources.getString(R.string.flying) -> return TypeEnum.flying
            context.resources.getString(R.string.poison) -> return TypeEnum.poison
            context.resources.getString(R.string.ground) -> return TypeEnum.ground
            context.resources.getString(R.string.rock) -> return TypeEnum.rock
            context.resources.getString(R.string.bug) -> return TypeEnum.bug
            context.resources.getString(R.string.ghost) -> return TypeEnum.ghost
            context.resources.getString(R.string.steel) -> return TypeEnum.steel
            context.resources.getString(R.string.fire) -> return TypeEnum.fire
            context.resources.getString(R.string.water) -> return TypeEnum.water
            context.resources.getString(R.string.grass) -> return TypeEnum.grass
            context.resources.getString(R.string.electric) -> return TypeEnum.electric
            context.resources.getString(R.string.psychic) -> return TypeEnum.psychic
            context.resources.getString(R.string.ice) -> return TypeEnum.ice
            context.resources.getString(R.string.dragon) -> return TypeEnum.dragon
            context.resources.getString(R.string.dark) -> return TypeEnum.dark
            context.resources.getString(R.string.fairy) -> return TypeEnum.fairy
            context.resources.getString(R.string.shadow) -> return TypeEnum.shadow
            else -> return TypeEnum.UNKNOWN
        }
    }

    fun getTypeLabel(type: TypeEnum): String {
        when (type) {
            TypeEnum.normal -> return context.resources.getString(R.string.normal)
            TypeEnum.fighting -> return context.resources.getString(R.string.fighting)
            TypeEnum.flying -> return context.resources.getString(R.string.flying)
            TypeEnum.poison -> return context.resources.getString(R.string.poison)
            TypeEnum.ground -> return context.resources.getString(R.string.ground)
            TypeEnum.rock -> return context.resources.getString(R.string.rock)
            TypeEnum.bug -> return context.resources.getString(R.string.bug)
            TypeEnum.ghost -> return context.resources.getString(R.string.ghost)
            TypeEnum.steel -> return context.resources.getString(R.string.steel)
            TypeEnum.fire -> return context.resources.getString(R.string.fire)
            TypeEnum.water -> return context.resources.getString(R.string.water)
            TypeEnum.grass -> return context.resources.getString(R.string.grass)
            TypeEnum.electric -> return context.resources.getString(R.string.electric)
            TypeEnum.psychic -> return context.resources.getString(R.string.psychic)
            TypeEnum.ice -> return context.resources.getString(R.string.ice)
            TypeEnum.dragon -> return context.resources.getString(R.string.dragon)
            TypeEnum.dark -> return context.resources.getString(R.string.dark)
            TypeEnum.fairy -> return context.resources.getString(R.string.fairy)
            TypeEnum.shadow -> return context.resources.getString(R.string.shadow)
            TypeEnum.UNKNOWN -> return context.resources.getString(R.string.unknown)
            TypeEnum.none -> return ""
        }
    }

    fun getTypeColor(type: TypeEnum): Int {
        when (type) {
            TypeEnum.normal -> return R.color.color_pokemon_normal
            TypeEnum.fighting -> return R.color.color_pokemon_fighting
            TypeEnum.flying -> return R.color.color_pokemon_flying
            TypeEnum.poison -> return R.color.color_pokemon_poison
            TypeEnum.ground -> return R.color.color_pokemon_ground
            TypeEnum.rock -> return R.color.color_pokemon_rock
            TypeEnum.bug -> return R.color.color_pokemon_bug
            TypeEnum.ghost -> return R.color.color_pokemon_ghost
            TypeEnum.steel -> return R.color.color_pokemon_steel
            TypeEnum.fire -> return R.color.color_pokemon_fire
            TypeEnum.water -> return R.color.color_pokemon_water
            TypeEnum.grass -> return R.color.color_pokemon_grass
            TypeEnum.electric -> return R.color.color_pokemon_electric
            TypeEnum.psychic -> return R.color.color_pokemon_psychic
            TypeEnum.ice -> return R.color.color_pokemon_ice
            TypeEnum.dragon -> return R.color.color_pokemon_dragon
            TypeEnum.dark -> return R.color.color_pokemon_dark
            TypeEnum.fairy -> return R.color.color_pokemon_fairy
            TypeEnum.shadow -> return R.color.color_text
            TypeEnum.UNKNOWN, TypeEnum.none -> return R.color.color_purple_secondary
        }
    }

}