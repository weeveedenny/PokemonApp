package com.olamachia.pokemonweekseventask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagon.android.sq007.SecondImplementation.PokemonViewModel
import com.google.android.material.snackbar.Snackbar
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

         // checks for the availability of network

        liveData.observe(this) { isAvailable ->
            when (isAvailable) {
                true -> {

                }
                false -> {
                    Toast.makeText(this, "Network Unavailable", Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
        viewModel.pokemonList.observe(
            this
        ) {
            database = it
            pokemonAdapter.setPokemonData(database)
            pokemonAdapter.notifyDataSetChanged()
        }
        recyclerView = findViewById(R.id.pokemon_display_recyclerView)
    }



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

}