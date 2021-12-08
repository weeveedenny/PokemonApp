package com.olamachia.pokemonweekseventask.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.olamachia.pokemonweekseventask.PokemonDisplayActivity
import com.olamachia.pokemonweekseventask.R
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, PokemonDisplayActivity::class.java)
            startActivity(intent)
        },3000)

    }


}

