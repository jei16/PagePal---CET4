package com.example.pagepal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class FirstSplashActivity : AppCompatActivity() {
    private val SPLASH_TIME: Long = 2500 // Adjust the duration of FirstSplashActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firstsplash)

        Handler().postDelayed(
            {
                val intent = Intent(this, splashscreen::class.java)
                startActivity(intent)
                finish()
            }, SPLASH_TIME)
    }
}