package com.olamachia.pokemonweekseventask


import com.olamachia.pokemonweekseventask.models.Pokemon
import com.olamachia.pokemonweekseventask.Model.PokemonData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonInterface {

    // get a range of pokemon
    @GET("pokemon")
    fun queryRange(@Query("limit") limit: Int?,
                         @Query("offset") offset: Int):
            Observable<PokemonData>


    // get specific url of each pokemon
    @GET("{url}")
    fun getPokemonDetails(@Path("url") url: String): Observable<Pokemon>


    // load pokemon on start
    @GET("pokemon/?offset=0&limit=50")
    fun getPokemonData(): Observable<PokemonData>
}