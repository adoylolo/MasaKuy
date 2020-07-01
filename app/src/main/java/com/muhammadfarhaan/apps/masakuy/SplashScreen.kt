package com.muhammadfarhaan.apps.masakuy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class SplashScreen : AppCompatActivity() {

    private lateinit var topAnim : Animation
    private lateinit var bottomAnim : Animation
    private lateinit var logo : ImageView
    private lateinit var text : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        //Hooks
        logo = findViewById(R.id.logo_splash_screen)
        text = findViewById(R.id.text_logo_splash)

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.topanim)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.botanim)

        logo.animation = topAnim
        text.animation = bottomAnim

        Handler().postDelayed({
            val intent = Intent(this@SplashScreen, Home::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}
