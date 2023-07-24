package com.example.pagepal

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pagepal.databinding.ActivityForgotpasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotpasswordBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var ResetPasswordEmail: EditText
    private lateinit var btnResetPassword: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotpasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ResetPasswordEmail = findViewById(R.id.editTxtResetPassEmail)
        btnResetPassword = findViewById(R.id.btnResetPass)

        firebaseAuth = FirebaseAuth.getInstance()

        btnResetPassword.setOnClickListener{

            val email = ResetPasswordEmail.text.toString()

            if (email.isNotEmpty()){
                firebaseAuth.sendPasswordResetEmail(email).addOnSuccessListener {
                Toast.makeText( this,"Please check your Email to reset your password.", Toast.LENGTH_SHORT).show()
                }
                    .addOnFailureListener{
                        Toast.makeText( this,it.toString(), Toast.LENGTH_SHORT).show()
                    }
            }else{
                Toast.makeText( this,"No Email provided.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}