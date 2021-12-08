package com.olamachia.pokemonweekseventask.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.olamachia.pokemonweekseventask.R
import com.olamachia.pokemonweekseventask.models.Ability

class PokemonAbilitiesAdapter(private var abilitiesList:List<Ability>):RecyclerView.Adapter<PokemonAbilitiesAdapter.AbilitiesViewHolder>() {

    inner class AbilitiesViewHolder(view: View):RecyclerView.ViewHolder(view) {
       val pokemonAbility: TextView = view.findViewById<TextView>(R.id.details_textview)
       fun bind(abilitiesList: List<Ability>, position: Int){
           pokemonAbility.text = abilitiesList[position].ability.name
       }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilitiesViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_details_text,parent,false)
        return AbilitiesViewHolder(inflater)
    }


    override fun getItemCount(): Int {
        return abilitiesList.size
    }


    override fun onBindViewHolder(holder: AbilitiesViewHolder, position: Int) {
        holder.bind(abilitiesList,position)
    }
}