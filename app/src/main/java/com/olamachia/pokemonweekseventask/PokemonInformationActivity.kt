package com.olamachia.pokemonweekseventask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.olamachia.pokemonweekseventask.ui.activities.PokemonDetailsFragment



class PokemonInformationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_information)

        intent.extras ?: return
        addFragmentToActivity(PokemonDetailsFragment())
    }


    private fun addFragmentToActivity(fragment : Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}