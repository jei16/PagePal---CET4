package com.example.pagepal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class getstarted2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_getstarted2)

        val backButtongetstarted2: Button = findViewById(R.id.backbutton1)
        backButtongetstarted2.setOnClickListener {
            val intent = Intent(this, getstarted::class.java)
            startActivity(intent)
        }

        val nextScreen2: Button = findViewById(R.id.nextbutton2)
        nextScreen2.setOnClickListener {
            val intent = Intent(this, getstarted3::class.java)
            startActivity(intent)
        }
    }
}