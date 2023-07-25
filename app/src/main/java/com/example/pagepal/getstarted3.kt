package com.example.pagepal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.pagepal.databinding.ActivityGetstarted3Binding

class getstarted3 : AppCompatActivity() {

    private lateinit var binding: ActivityGetstarted3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetstarted3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backbutton2.setOnClickListener {
            val intent = Intent(this, getstarted2::class.java)
            startActivity(intent)

            // Remove animation when going back to getstarted2 activity
            overridePendingTransition(0, 0)
        }

        binding.nextbutton3.setOnClickListener {
            val intent = Intent(this, getstarted4::class.java)
            startActivity(intent)

            // Remove animation when going to getstarted4 activity
            overridePendingTransition(0, 0)
        }
    }
}
