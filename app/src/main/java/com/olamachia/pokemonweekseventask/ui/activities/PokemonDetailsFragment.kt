package com.olamachia.pokemonweekseventask.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.olamachia.pokemonweekseventask.Model.PokemonData
import com.olamachia.pokemonweekseventask.PokemonViewModel
import com.olamachia.pokemonweekseventask.R
import com.olamachia.pokemonweekseventask.RetrofitClient
import com.olamachia.pokemonweekseventask.adapters.PokemonAbilitiesAdapter
import com.olamachia.pokemonweekseventask.adapters.PokemonMovesAdapter
import com.olamachia.pokemonweekseventask.adapters.PokemonStatAdapter
import com.olamachia.pokemonweekseventask.models.Pokemon

class PokemonDetailsFragment : Fragment() {

    private lateinit var abilityRecyclerView: RecyclerView
    private lateinit var moveRecyclerView: RecyclerView
    private lateinit var statRecyclerView: RecyclerView
    private lateinit var pokemonInfoImage: ImageView
    private val viewModel:PokemonViewModel by activityViewModels()
    private lateinit var pos : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data  = requireActivity().intent.extras
        pos = data?.getString("id").toString()

        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                requireActivity().finish()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokemonInfoImage = view?.findViewById(R.id.PokemonDetailsImage)!!

        viewModel.getPokemonDetails(pos)


        val pokemonName = view.findViewById<TextView>(R.id.pokemon_details_name_textView)
        abilityRecyclerView = view.findViewById(R.id.pokemon_abilities_recyclerview)
        moveRecyclerView = view.findViewById(R.id.moves_recyclerview)
        statRecyclerView = view.findViewById(R.id.stats_recyclerview)

        Glide.with(this).load(
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/${pos}.png")
            .into(pokemonInfoImage)


        val pokemonObserver = Observer<Pokemon> { pokemon ->
            // Update the UI, in this case, a TextView
            pokemonName?.text = pokemon.species.name
            val statAdapter =  PokemonStatAdapter(pokemon.stats)
            val abilitiesAdapter = PokemonAbilitiesAdapter(pokemon.abilities)
            val movesAdapter = PokemonMovesAdapter(pokemon.moves)

            abilityRecyclerView.adapter = abilitiesAdapter
            moveRecyclerView.adapter = movesAdapter
            statRecyclerView.adapter = statAdapter

            abilityRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            moveRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            statRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        viewModel.pokemonDetails.observe(viewLifecycleOwner, pokemonObserver)


    }


    private fun addFragmentToActivity(fragment : Fragment) {
        val transaction =  fragmentManager?.beginTransaction() // ---------------------------
        transaction?.replace(R.id.fragment_container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()

    }

}