package com.example.pagepal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.pagepal.databinding.ActivitySplashscreenBinding

class splashscreen : AppCompatActivity() {

    private val SPLASH_TIME: Long = 3000
    private lateinit var binding: ActivitySplashscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed(
            {
                startActivity(Intent (this, getstarted::class.java))
                finish()
            }, SPLASH_TIME)

    }
}