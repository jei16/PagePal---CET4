package com.example.pagepal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class getstarted3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_getstarted3)

        val backButtongetstarted3: Button = findViewById(R.id.backbutton2)
        backButtongetstarted3.setOnClickListener {
            val intent = Intent(this, getstarted2::class.java)
            startActivity(intent)
        }

        val nextScreen3: Button = findViewById(R.id.nextbutton3)
        nextScreen3.setOnClickListener {
            val intent = Intent(this, getstarted4::class.java)
            startActivity(intent)
        }
    }
}