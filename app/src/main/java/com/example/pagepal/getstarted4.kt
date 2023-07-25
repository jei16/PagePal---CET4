package com.example.pagepal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.example.pagepal.databinding.ActivityGetstarted4Binding

class getstarted4 : AppCompatActivity() {

    private lateinit var binding: ActivityGetstarted4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetstarted4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backbutton3.setOnClickListener {
            val intent = Intent(this, getstarted3::class.java)
            startActivity(intent)

            // Remove animation when going back to getstarted3 activity
            overridePendingTransition(0, 0)
        }

        binding.nextbutton4.setOnClickListener {
            val intent = Intent(this, LoginScreenActivity::class.java)
            startActivity(intent)

            // Apply a popping animation when starting LoginScreenActivity
            val anim = AnimationUtils.loadAnimation(this, R.anim.popp)
            binding.nextbutton4.startAnimation(anim)
        }
    }
}
