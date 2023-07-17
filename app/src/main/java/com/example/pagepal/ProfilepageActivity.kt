package com.example.pagepal

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class  ProfilepageActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)

        val profileDrawer = findViewById<DrawerLayout>(R.id.ProfileDrawer)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toggle = ActionBarDrawerToggle(this, profileDrawer, toolbar,  R.string.open, R.string.close)
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


     override fun onNavigationItemSelected(item: MenuItem): Boolean {

         val profileDrawer = findViewById<DrawerLayout>(R.id.ProfileDrawer)
        when(item.itemId){
            R.id.sliding_menu_borrowandlending -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BorrowFragment()).commit()
        }
        profileDrawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {

        val profileDrawer = findViewById<DrawerLayout>(R.id.ProfileDrawer)
        if (profileDrawer.isDrawerOpen(GravityCompat.START)){
            profileDrawer.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }

    }


}