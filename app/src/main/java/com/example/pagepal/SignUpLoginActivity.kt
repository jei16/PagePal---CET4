package com.example.pagepal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pagepal.databinding.ActivitySignupscreen2Binding
import com.example.pagepal.databinding.ActivitySignupscreenBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpLoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignupscreenBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signinlink.setOnClickListener{
            val intent = Intent(this, LoginScreenActivity::class.java)
            startActivity(intent)
        }

        binding.signupBtn.setOnClickListener{
            val intent = Intent (this, ActivitySignupscreen2Binding::class.java)
            startActivity(intent)
        }

        


    }

}