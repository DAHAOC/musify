package com.musify.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.musify.R
import com.musify.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val originalPaddingStart = binding.root.paddingTop
        val originalPaddingTop = binding.root.paddingTop
        val originalPaddingEnd = binding.root.paddingEnd

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                originalPaddingStart + systemBars.left,
                originalPaddingTop + systemBars.top,
                originalPaddingEnd + systemBars.right,
                0
            )
            insets
        }

        val navView = binding.navView

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        navView.setupWithNavController(navController)

        val drawerLayout = binding.drawerLayout
        val navDrawer = binding.navDrawer
        navDrawer.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_settings -> {
                    Toast.makeText(this, "Ajustes", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
            drawerLayout.closeDrawers()
            true
        }
    }

    fun openDrawer() {
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }
}