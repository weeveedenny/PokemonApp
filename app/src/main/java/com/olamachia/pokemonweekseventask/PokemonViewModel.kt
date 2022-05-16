package com.olamachia.pokemonweekseventask

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olamachia.pokemonweekseventask.Model.PokemonData
import com.olamachia.pokemonweekseventask.Model.PokemonDataGotten
import com.olamachia.pokemonweekseventask.models.Pokemon
import kotlinx.coroutines.launch
import java.lang.Exception

class PokemonViewModel : ViewModel() {
    val pokemonList: MutableLiveData<List<PokemonDataGotten>> by lazy {

        // initialize mutable live data on the list to be watched
        MutableLiveData<List<PokemonDataGotten>>()
    }

    private val _pokemonResponse = MutableLiveData<PokemonData>()
    val pokemonResponse: LiveData<PokemonData> get() = _pokemonResponse

    private val _pokemonDetails = MutableLiveData<Pokemon>()
    val pokemonDetails:LiveData<Pokemon> get() = _pokemonDetails

   // triggering the network call
    fun loadAllPokemon() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.retroApiService.getPokemonData()
                if (response.isSuccessful && response.body() != null) _pokemonResponse.value =
                    response.body()
            } catch (e: Exception) {
                Log.d("LoadingPokemonException", "exception ------ $e ")
            }
        }
    }


//
    fun getPokemonDetails(pos:String){
        viewModelScope.launch {
            try {
                val response = RetrofitClient.retroApiService.getPokemonDetails("pokemon/$pos")
                if (response.isSuccessful && response.body() != null) _pokemonDetails.value = response.body()
            }catch (e:Exception){
                e.stackTrace
                Log.d("PokemonDetailsException", "exception ------ $e ")
            }
        }
    }

}