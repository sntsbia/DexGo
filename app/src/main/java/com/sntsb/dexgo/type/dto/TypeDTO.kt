package com.sntsb.dexgo.type.dto

data class TypeDTO(
    val id: Int, val description: String, val image: String
) {
    override fun toString(): String {
        return "TypeDTO(id=$id, description='$description', image='$image')"
    }
}
