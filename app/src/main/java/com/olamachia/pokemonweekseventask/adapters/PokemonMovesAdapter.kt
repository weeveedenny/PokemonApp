package com.olamachia.pokemonweekseventask.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.olamachia.pokemonweekseventask.R
import com.olamachia.pokemonweekseventask.models.Move

class PokemonMovesAdapter(var pokemonMovesList:List<Move>):RecyclerView.Adapter<PokemonMovesAdapter.PokemonMovesViewHolder>() {

   inner class PokemonMovesViewHolder(view: View):RecyclerView.ViewHolder(view) {
       val moves = view.findViewById<TextView>(R.id.details_textview)
       fun bind(moveList:List<Move>, position:Int){
           moves.text = moveList[position].move.name
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonMovesViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_details_text,parent,false)
        return PokemonMovesViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return pokemonMovesList.size
    }

    override fun onBindViewHolder(holder: PokemonMovesViewHolder, position: Int) {
        holder.bind(pokemonMovesList, position)
    }
}