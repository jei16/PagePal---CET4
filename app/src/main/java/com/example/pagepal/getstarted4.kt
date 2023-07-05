package com.example.pagepal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.pagepal.databinding.ActivityGetstarted4Binding

class getstarted4 : AppCompatActivity() {

    private lateinit var binding : ActivityGetstarted4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetstarted4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backbutton3.setOnClickListener{
            val intent = Intent(this, getstarted3::class.java)
            startActivity(intent)
        }

        binding.nextbutton4.setOnClickListener{
            val intent = Intent(this, LoginScreenActivity::class.java)
            startActivity(intent)
        }
    }
}
