package com.example.pagepal

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.pagepal.databinding.LayoutBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: LayoutBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =LayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(ProfileFragment())

        val mainDrawer = findViewById<DrawerLayout>(R.id.MainDrawer)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toggle = ActionBarDrawerToggle(this, mainDrawer, toolbar, R.string.open, R.string.close)
        mainDrawer.addDrawerListener(toggle)

        toggle.syncState()

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.profile -> replaceFragment(ProfileFragment())
                else->{

            }


        }
            true
        }


    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_container,fragment)
        fragmentTransaction.commit()
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val profileDrawer = findViewById<DrawerLayout>(R.id.MainDrawer)
        when (item.itemId) {
            R.id.sliding_menu_settings -> supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, SettingsFragment()).commit()
            R.id.sliding_menu_borrowandlending -> supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, BorrowFragment()).commit()

        }
        profileDrawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {

        val mainDrawer = findViewById<DrawerLayout>(R.id.MainDrawer)
        if (mainDrawer.isDrawerOpen(GravityCompat.START)) {
            mainDrawer.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }

    }
}




