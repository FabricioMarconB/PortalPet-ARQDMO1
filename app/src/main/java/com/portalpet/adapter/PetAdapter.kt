package com.portalpet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.portalpet.databinding.ItemPetBinding
import com.portalpet.model.Pet

class PetAdapter(
    context: Context,
    private val lista: List<Pet>
) : ArrayAdapter<Pet>(context, 0, lista) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemPetBinding
        val itemView: View

        if (convertView == null) {
            binding = ItemPetBinding.inflate(LayoutInflater.from(context), parent, false)
            itemView = binding.root
            itemView.tag = binding
        } else {
            itemView = convertView
            binding = itemView.tag as ItemPetBinding
        }

        val pet = lista[position]
        binding.imgFotoPet.setImageResource(pet.foto)
        binding.tvNomePet.text = pet.nome
        binding.tvEspecieRaca.text = pet.especieRaca
        binding.tvIdadePet.text = pet.idade

        return itemView
    }
}