package com.olamachia.pokemonweekseventask.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import androidx.constraintlayout.widget.ConstraintLayout
import com.olamachia.pokemonweekseventask.ClickListener
import com.olamachia.pokemonweekseventask.Model.PokemonDataGotten
import com.olamachia.pokemonweekseventask.R

class PokemonAdapter(var context: Context, var listener: ClickListener): RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    var pokemonList:List<PokemonDataGotten> = listOf()

    //Initialize views
    inner  class PokemonViewHolder(var view: View):RecyclerView.ViewHolder(view) {
       val pokemonImage = view.findViewById<ImageView>(R.id.pokemon_card_imageview)
       val pokemonName = view.findViewById<TextView>(R.id.pokemon_name_textview)
       val pokemonLayout = view.findViewById<ConstraintLayout>(R.id.pokemon_card)
    }

    fun setPokemonData(recyclerList:List<PokemonDataGotten>){
        pokemonList = recyclerList
        notifyDataSetChanged()
    }

     //Returns the layout to be inflated
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_display_layout,parent,false)
        return PokemonViewHolder(inflater)
    }

    //Specifies the list size
    override fun getItemCount(): Int {
        return pokemonList.size
    }

    //This binds the views to their values
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        var pos = position+ 1
        holder.pokemonName.text = pokemonList[position].name
        val pokemonImage = holder.pokemonImage
        Glide.with(context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/${pos}.png")
            .into(pokemonImage)
        holder.pokemonLayout.setOnClickListener {
            listener.onItemClicked(pos)
        }
    }


}