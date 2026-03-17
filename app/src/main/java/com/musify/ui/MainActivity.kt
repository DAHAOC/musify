package com.musify.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
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

        // Setup header
        val headerView = navDrawer.getHeaderView(0)
        val headerUserIcon = headerView.findViewById<com.google.android.material.imageview.ShapeableImageView>(R.id.header_user_icon)
        val headerUsername = headerView.findViewById<android.widget.TextView>(R.id.header_username)
        val viewProfile = headerView.findViewById<android.widget.TextView>(R.id.view_profile)

        // Load user data
        val userImage = "https://cdn.pfps.gg/pfps/1957-patrick-star-profile-photo.png"
        Glide.with(this).load(userImage).centerCrop()
            .placeholder(R.drawable.ic_person)
            .transform(RoundedCorners(resources.getDimensionPixelSize(R.dimen.radius_medium)))
            .into(headerUserIcon)
        headerUsername.text = getString(R.string.user)

        viewProfile.setOnClickListener {
            val navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment).navController
            navController.navigate(R.id.navigation_profile)
            drawerLayout.closeDrawers()
        }

        // Apply insets to header
        ViewCompat.setOnApplyWindowInsetsListener(headerView) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, view.paddingBottom)
            insets
        }

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