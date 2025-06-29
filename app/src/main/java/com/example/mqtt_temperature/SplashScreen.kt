package com.example.mqtt_temperature

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v("TAG", "Primer LOG")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        Handler().postDelayed({
            val signInIntent = Intent(this, MainActivity::class.java)
            startActivity(signInIntent)
            finish()

        }, 2000)

    }
}