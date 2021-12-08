package com.olamachia.pokemonweekseventask.models

import com.google.gson.annotations.SerializedName

data class PokemonApiResponse(
    val count: Int,
    val next: String?,
    val previous: Any,
    @SerializedName("results")
    val pokemonItem: List<PokeMonItem>
)