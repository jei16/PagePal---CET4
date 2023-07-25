package com.example.pagepal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import com.example.pagepal.databinding.ActivityGetstartedBinding

class getstarted : AppCompatActivity() {

    private lateinit var binding: ActivityGetstartedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGetstartedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginbutton1.setOnClickListener {
            val intent = Intent(this, LoginScreenActivity::class.java)
            startActivity(intent)
        }

        binding.nextbutton1.setOnClickListener {
            val intent = Intent(this, getstarted2::class.java)
            startActivity(intent)

            overridePendingTransition(0, 0)
        }
    }
}
