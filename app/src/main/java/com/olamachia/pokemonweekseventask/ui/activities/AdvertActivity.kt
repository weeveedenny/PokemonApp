package com.olamachia.pokemonweekseventask.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import com.airbnb.lottie.LottieAnimationView
import com.olamachia.pokemonweekseventask.R

class AdvertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advert)

        Handler(Looper.getMainLooper()).postDelayed({
            val animationView: LottieAnimationView = findViewById(R.id.activity_advert_animationView)
            animationView.playAnimation()

            Handler(Looper.getMainLooper()).postDelayed({
//                intent = Intent(this, MyRecyclerViewActivity::class.java)
                startActivity(intent)
            },7000)
        }, 5000)

        val slider = AnimationUtils.loadAnimation( this, R.anim.fade_in)
        val slided = findViewById<View>(R.id.activity_advert_textView)
        slided.startAnimation(slider)

    }

}