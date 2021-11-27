package com.olamachia.pokemonweekseventask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.airbnb.lottie.LottieAnimationView

class AdvertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advert)

        Handler(Looper.getMainLooper()).postDelayed({
            val animationView: LottieAnimationView = findViewById(R.id.animationView)
            animationView.playAnimation()

            Handler(Looper.getMainLooper()).postDelayed({
                intent = Intent(this, MyRecyclerViewActivity::class.java)
                startActivity(intent)
            },5000)
        }, 3000)



    }
}