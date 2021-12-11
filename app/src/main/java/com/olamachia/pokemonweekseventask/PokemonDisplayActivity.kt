package com.olamachia.pokemonweekseventask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.olamachia.pokemonweekseventask.Model.PokemonDataGotten
import com.olamachia.pokemonweekseventask.adapters.PokemonAdapter
import io.reactivex.disposables.CompositeDisposable
import java.lang.Exception

class PokemonDisplayActivity : AppCompatActivity(), ClickListener {
    private lateinit var pokemonAdapter: PokemonAdapter
    private lateinit var viewModel: PokemonViewModel
    private lateinit var database: List<PokemonDataGotten>
    private lateinit var compositeDisposable: CompositeDisposable
    private lateinit var recyclerView: RecyclerView
    private lateinit var liveData: LiveData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_display)

        //Created an instance of CompositeDisposable Declared an empty list database
        database = listOf()
        liveData = LiveData(application)

        pokemonAdapter = PokemonAdapter(this, this)
        isNetwork()

        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
        viewModel.pokemonList.observe(
            this
        ) {
            database = it
            pokemonAdapter.setPokemonData(database)
            pokemonAdapter.notifyDataSetChanged()
        }
        recyclerView = findViewById(R.id.pokemon_display_recyclerView)

        var searchView = findViewById<SearchView>(R.id.pokemon_display_searchView)

        //For Searching number of items to display
        searchView.setOnQueryTextListener(
            object: SearchView.OnQueryTextListener{

                override fun onQueryTextSubmit(query: String?): Boolean {
                  return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.toInt()?.let { search(it) }
                    return true
                }
            }
        )

    }

    /**
     * Creates an instance of a composite disposal
     * Adds the retrofit call to get the composite disposal
     * Makes the call on the background Subscribers.io thread
     * Observes the result on the main thread
     */

    private fun loadAllPokemon() {
        try {
            compositeDisposable = CompositeDisposable()
            compositeDisposable.add(
                RetrofitClient.retroApiService.getPokemonData()
                    .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                    .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                    .subscribe {
                        pokemonAdapter.setPokemonData(database)
                        viewModel.pokemonList.value = it.results
                        recyclerView.adapter = pokemonAdapter
                        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
                        Log.d("resultz", it.results.toString())
                    }
            )
        } catch (e: Exception) {
            e.message?.let { Toast.makeText(this, "${e.message}", Toast.LENGTH_SHORT).show() }
        }
    }

    //Sets the intent for the recyclerview item clicked
     //also Passes the position of the recyclerview item clicked to the next activity


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


    private fun isNetwork(){
        // checks for the availability of network

        liveData.observe(this) { isAvailable ->
            when (isAvailable) {
                true -> {
                    loadAllPokemon()
                }
                false -> {
                    Toast.makeText(this, "Network Unavailable", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


}