package com.olamachia.pokemonweekseventask.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.olamachia.pokemonweekseventask.R
import com.olamachia.pokemonweekseventask.models.Stat

class PokemonStatAdapter(var pokemonStatsList:List<Stat>):RecyclerView.Adapter<PokemonStatAdapter.PokemonStatViewHolder>() {

    class PokemonStatViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val stat = view.findViewById<TextView>(R.id.details_textview)
        fun bind(pokemonStatsList: List<Stat>, position:Int){
            stat.text = pokemonStatsList[position].stat.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonStatViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_details_text, parent, false)
        return PokemonStatViewHolder(
            inflater
        )
    }

    override fun getItemCount(): Int {
        return pokemonStatsList.size
    }

    override fun onBindViewHolder(holder: PokemonStatViewHolder, position: Int) {
        holder.bind(pokemonStatsList,position)
    }
}