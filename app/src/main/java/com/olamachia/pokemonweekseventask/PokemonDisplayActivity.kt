package com.olamachia.pokemonweekseventask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.olamachia.pokemonweekseventask.Model.PokemonData
import com.olamachia.pokemonweekseventask.Model.PokemonDataGotten
import com.olamachia.pokemonweekseventask.adapters.PokemonAdapter
import io.reactivex.disposables.CompositeDisposable

class PokemonDisplayActivity : AppCompatActivity(), ClickListener {
    private lateinit var pokemonAdapter: PokemonAdapter

    private lateinit var database: List<PokemonDataGotten>
    private lateinit var compositeDisposable: CompositeDisposable
    private lateinit var recyclerView: RecyclerView
    private lateinit var networkAvailabilityCheck: NetworkAvailabilityCheck
    private val viewModel:PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_display)


        database = listOf()
        networkAvailabilityCheck = NetworkAvailabilityCheck(application)

        pokemonAdapter = PokemonAdapter(this, this)

        networkAvailabilityCheck.observe(this) { isAvailable ->
            when (isAvailable) {
                true -> {
                    Toast.makeText(this, "Network is Available", Toast.LENGTH_LONG).show()
                    viewModel.loadAllPokemon()
                }
                false -> {
                    Toast.makeText(this, "Network Unavailable", Toast.LENGTH_LONG).show()
                }
            }
        }


        viewModel.pokemonList.observe(
            this
        ) {
            database = it
            pokemonAdapter.setPokemonData(database)
            pokemonAdapter.notifyDataSetChanged()
        }

        // initializing views
        recyclerView = findViewById(R.id.pokemon_display_recyclerView)
        var searchView = findViewById<SearchView>(R.id.pokemon_display_searchView)

        //For Searching number of items to display
        searchView.setOnQueryTextListener(
            object: SearchView.OnQueryTextListener{

                override fun onQueryTextSubmit(query: String?): Boolean {
                  return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    return true
                }
            }
        )


        val pokemonObserver = Observer<PokemonData> { pokemonData ->

            pokemonAdapter.setPokemonData(pokemonData.results)
            recyclerView.adapter = pokemonAdapter
            recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)

        }

        viewModel.pokemonResponse.observe(this, pokemonObserver)
    }



    override fun onItemClicked(position: Int) {
        val intent = Intent(this, PokemonInformationActivity::class.java)
        intent.putExtra("id", position.toString())
        startActivity(intent)
    }
  fun search(num: Int){
     val figure = viewModel.pokemonList.value?.take(num)

     if (figure != null) {
         pokemonAdapter.setPokemonData(figure)

     }

 }




}