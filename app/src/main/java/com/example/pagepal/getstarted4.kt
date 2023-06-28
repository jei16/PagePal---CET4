package com.example.pagepal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class getstarted4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_getstarted4)

        val backButtongetstarted4: Button = findViewById(R.id.backbutton3)
        backButtongetstarted4.setOnClickListener {
            val intent = Intent(this, getstarted3::class.java)
            startActivity(intent)
        }

        val nextScreen4: Button = findViewById(R.id.nextbutton4)
        nextScreen4.setOnClickListener {
            val intent = Intent(this, LoginScreenActivity::class.java)
            startActivity(intent)
        }
    }
}
