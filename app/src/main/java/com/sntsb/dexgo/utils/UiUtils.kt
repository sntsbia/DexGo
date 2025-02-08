package com.sntsb.dexgo.utils

import android.content.Context
import android.util.Log
import com.sntsb.dexgo.R
import com.sntsb.dexgo.type.enums.TypeEnum

class UiUtils(val context: Context) {

    companion object {
        private const val TAG = "UiUtils"
    }

    fun getStatusLabel(status: String): String {
        Log.e(TAG, "getStatusLabel: $status")
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

    fun getTypeColor(tipo: TypeEnum): Int {
        when (tipo) {
            TypeEnum.normal -> return R.color.color_normal
            TypeEnum.fighting -> return R.color.color_fighting
            TypeEnum.flying -> return R.color.color_flying
            TypeEnum.poison -> return R.color.color_poison
            TypeEnum.ground -> return R.color.color_ground
            TypeEnum.rock -> return R.color.color_rock
            TypeEnum.bug -> return R.color.color_bug
            TypeEnum.ghost -> return R.color.color_ghost
            TypeEnum.steel -> return R.color.color_steel
            TypeEnum.fire -> return R.color.color_fire
            TypeEnum.water -> return R.color.color_water
            TypeEnum.grass -> return R.color.color_grass
            TypeEnum.electric -> return R.color.color_electric
            TypeEnum.psychic -> return R.color.color_psychic
            TypeEnum.ice -> return R.color.color_ice
            TypeEnum.dragon -> return R.color.color_dragon
            TypeEnum.dark -> return R.color.color_dark
            TypeEnum.fairy -> return R.color.color_fairy
            TypeEnum.UNKNOWN -> return R.color.color_secondary
        }
    }

}