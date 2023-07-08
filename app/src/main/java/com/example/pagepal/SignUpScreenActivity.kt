package com.example.pagepal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import com.example.pagepal.databinding.ActivitySignupscreen3Binding
import com.google.firebase.auth.FirebaseAuth

class SignUpScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignupscreen3Binding
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupscreen3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()


        binding.signupButton3.setOnClickListener{
            val email = binding.usernameEditText3.text.toString()
            val password = binding.passwordEditText3.text.toString()
            val confirmpassword = binding.confirmpasswordEditText3.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmpassword.isNotEmpty()){
                if (password == confirmpassword) {

                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        if(it.isSuccessful) {
                            val intent = Intent(this, LoginScreenActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
                }
            }else{
                    Toast.makeText(this, "Please fill in the blanks", Toast.LENGTH_SHORT).show()
                }
            }
        }

}
