package com.example.pagepal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.pagepal.databinding.ActivityGetstarted2Binding

class getstarted2 : AppCompatActivity() {

    private lateinit var binding:ActivityGetstarted2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetstarted2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.backbutton1.setOnClickListener{
            val intent = Intent(this, getstarted::class.java)
            startActivity(intent)
        }

        binding.nextbutton2.setOnClickListener(){
            val intent = Intent(this, getstarted3::class.java)
            startActivity(intent)
        }
    }
}