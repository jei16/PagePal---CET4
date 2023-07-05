package com.example.pagepal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.pagepal.databinding.ActivityLoginscreenBinding
import com.google.firebase.auth.FirebaseAuth

class LoginScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginscreenBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        val signupScreen: TextView = findViewById(R.id.sign_up_text)
        signupScreen.setOnClickListener {
            val intent = Intent(this, SignUpScreenActivity::class.java)
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener{
            val username = binding.usernameEdittext.text.toString()
            val password = binding.passwordEdittext.text.toString()


            if (username.isNotEmpty() && password.isNotEmpty()){

                    firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener {
                        if(it.isSuccessful) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
            }else{
                Toast.makeText(this, "Please fill in the blanks", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        if(firebaseAuth.currentUser !=null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}