package com.example.pagepal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pagepal.ui.theme.PagePalTheme
import android.content.Intent
import android.widget.Button

class MainActivity : ComponentActivity() {
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




