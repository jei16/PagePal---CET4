package com.example.pagepal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import com.example.pagepal.databinding.ActivitySignupscreenBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupscreenBinding
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signupBtn.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty()) {
                firebaseAuth.createUserWithEmailAndPassword(email, name)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(this, LoginScreenActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please fill in the blanks", Toast.LENGTH_SHORT).show()
            }
        }

    }

}
