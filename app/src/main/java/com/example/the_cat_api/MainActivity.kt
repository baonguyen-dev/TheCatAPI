package com.example.the_cat_api

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.the_cat_api.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private var currentNavId = NAV_ID_NONE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            navHostFragment =
                supportFragmentManager.findFragmentById(R.id.fcv_nav_host_fragment) as NavHostFragment
        } catch (e: Exception) {
            Log.e("MainActivity","navHostFragment: $e")
        }

        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            currentNavId = destination.id
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (currentNavId == R.id.BreedsFragment) {
            super.onBackPressed()
        }
        else {
            navController.navigateUp()
        }
    }

    companion object {
        /** Key for an int extra defining the initial navigation target. */
        private const val NAV_ID_NONE = -1
    }
}