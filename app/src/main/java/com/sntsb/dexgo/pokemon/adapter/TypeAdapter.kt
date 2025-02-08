package com.sntsb.dexgo.pokemon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sntsb.dexgo.databinding.ItemTypeBinding
import com.sntsb.dexgo.type.dto.TypeDTO

class TypeAdapter(private val typeDTOList: List<TypeDTO>, val context: Context) :
    RecyclerView.Adapter<TypeAdapter.TypeItemViewHolder>() {

    inner class TypeItemViewHolder(private val databinding: ItemTypeBinding) :
        RecyclerView.ViewHolder(databinding.root) {

        fun bind(tipo: TypeDTO) {
            databinding.typeItemDataBinding = tipo

            Glide.with(databinding.root).load(tipo.image).into(databinding.ivType)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeItemViewHolder {
        val binding = ItemTypeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TypeItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return typeDTOList.size
    }

    override fun onBindViewHolder(holder: TypeItemViewHolder, position: Int) {
        val typeDTO = typeDTOList[position]
        holder.bind(typeDTO)
    }


}