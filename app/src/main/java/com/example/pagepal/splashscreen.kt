package com.example.pagepal

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.pagepal.databinding.ActivitySplashscreenBinding
import com.google.firebase.auth.FirebaseAuth

class splashscreen : AppCompatActivity() {

    private val SPLASH_TIME: Long = 3000
    private lateinit var binding: ActivitySplashscreenBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize FirebaseAuth instance
        firebaseAuth = FirebaseAuth.getInstance()

        Handler().postDelayed(
            {
                val Verify = firebaseAuth.currentUser?.isEmailVerified

                if (firebaseAuth.currentUser != null && Verify == true ) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // User is not authenticated, so start GetStartedActivity and finish the SplashActivity
                    val intent = Intent(this, getstarted::class.java)
                    startActivity(intent)
                    finish()
                }
            }, SPLASH_TIME)
    }
}