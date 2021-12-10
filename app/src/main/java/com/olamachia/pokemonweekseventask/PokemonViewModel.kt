package com.olamachia.pokemonweekseventask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olamachia.pokemonweekseventask.Model.PokemonDataGotten

class PokemonViewModel:ViewModel() {
    val pokemonList: MutableLiveData<List<PokemonDataGotten>> by lazy {

        // initialize mutable live data on the list to be watched
        MutableLiveData<List<PokemonDataGotten>>()
    }
}