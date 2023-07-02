package com.example.pagepal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button


class getstarted : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_getstarted)


        val openLoginScreen: Button = findViewById(R.id.loginbutton1)
        openLoginScreen.setOnClickListener {
            val intent = Intent(this, LoginScreenActivity::class.java)
            startActivity(intent)
        }
        val nextScreen: Button = findViewById(R.id.nextbutton1)
        nextScreen.setOnClickListener {
            val intent = Intent(this, getstarted2::class.java)
            startActivity(intent)
        }

    }
}