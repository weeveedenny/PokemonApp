package com.olamachia.pokemonweekseventask.models

data class HeldItem(
    val item: PokeMonItem,
    val version_details: List<VersionDetail>
)