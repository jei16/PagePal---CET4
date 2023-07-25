package com.example.pagepal

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pagepal.databinding.ActivityLoginscreenBinding
import com.example.pagepal.databinding.ActivityProfilePageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginscreenBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var logAttempts = 0
    private var logAttemptTime: Long = 0


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


        val forgotpasswordScreen: TextView = findViewById(R.id.forgot_password_text)
        forgotpasswordScreen.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener {
            val email = binding.usernameEdittext.text.toString()
            val password = binding.passwordEdittext.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                val logAttemptLimit = 3
                val logAttemptWindow = 120000L

                getUserIDFromEmail(email) { userID ->
                    if (userID != null){
                        val currentTime = System.currentTimeMillis()
                        if (logAttempts < logAttemptLimit || currentTime - logAttemptTime >= logAttemptWindow){
                            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                                if (it.isSuccessful){
                                    val Verify = firebaseAuth.currentUser?.isEmailVerified
                                    if (Verify == true){
                                        val intent = Intent(this, MainActivity::class.java)
                                        startActivity(intent)
                                    }else{
                                        Toast.makeText(this, "Please Verify your Email", Toast.LENGTH_SHORT).show()
                                    }
                                }else{
                                    Toast.makeText(this, "Login Failed, Please check your password.", Toast.LENGTH_SHORT).show()
                                    logAttempts++
                                    logAttemptTime = currentTime
                                }
                            }
                        }else{
                            Toast.makeText(this, "Try Again in 2 minutes.", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(this, "Email is not found", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Please fill in the blanks", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun getUserIDFromEmail(email: String, callback: (String?) -> Unit) {
            val db = FirebaseDatabase.getInstance().reference
            val uRef = db.child("users")

            uRef.orderByChild("email").equalTo(email)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            for (userSnapshot in snapshot.children) {
                                val userID = userSnapshot.key
                                callback(userID)
                                return
                            }
                        }
                        callback(null)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        callback(null)
                    }
                })
            }
    }