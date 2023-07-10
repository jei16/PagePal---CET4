package com.example.pagepal.ui.theme

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.pagepal.R

class ProfilepageActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)

        val profileDrawer = findViewById<DrawerLayout>(R.id.ProfileDrawer)

        toggle = ActionBarDrawerToggle(this, profileDrawer, R.string.open, R.string.close)
        profileDrawer.addDrawerListener(toggle)
        toggle.syncState()

       setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)




    }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
            return super.onOptionsItemSelected(item)
    }




}