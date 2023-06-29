package com.example.pagepal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.TextView

class LoginScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginscreen)

        val signupScreen: TextView = findViewById(R.id.sign_up_text)
        signupScreen.setOnClickListener {
            val intent = Intent(this, SignUpScreenActivity::class.java)
            startActivity(intent)
        }
    }
}