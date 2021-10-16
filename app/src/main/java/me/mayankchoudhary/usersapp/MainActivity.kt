package me.mayankchoudhary.usersapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import me.mayankchoudhary.usersapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // add nav controller
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // add bindings
        val binding = ActivityMainBinding.inflate(layoutInflater)
        // get reference to the root view
        val view = binding.root
        // pass the generated view
        setContentView(view)

        // setup nav Controller
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment

        navController = navHostFragment.navController
    }
}