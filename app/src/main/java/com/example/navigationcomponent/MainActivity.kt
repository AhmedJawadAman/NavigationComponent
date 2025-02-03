package com.example.navigationcomponent

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.navigationcomponent.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = findViewById(R.id.drawer_layout) // Initialize DrawerLayout
        navigationView = findViewById(R.id.navigation_view) // Initialize NavigationView
        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)

        // Set up Navigation Component
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        val navController = navHostFragment?.navController

        if (navController != null) {
            val appBarConfiguration = AppBarConfiguration(
                setOf(R.id.firstFragment), // Define top-level destination
                drawerLayout // Enables Drawer toggle
            )

            // Link Toolbar with NavController
            topAppBar.setupWithNavController(navController, appBarConfiguration)

            // Set Drawer Toggle (Hamburger Icon)
            val toggle = ActionBarDrawerToggle(
                this, drawerLayout, topAppBar,
                R.string.open_drawer, R.string.close_drawer
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            // Handle Navigation Destination Change (Title + Icon)
            navController.addOnDestinationChangedListener { _, destination, _ ->
                topAppBar.title = destination.label ?: getString(R.string.app_name)

                if (destination.id == R.id.firstFragment) {
                    topAppBar.navigationIcon = getDrawable(R.drawable.ic_menu) // Show menu icon
                    topAppBar.setNavigationOnClickListener {
                        drawerLayout.open() // Open drawer on menu icon click
                    }
                } else {
                    topAppBar.navigationIcon = getDrawable(R.drawable.ic_arrow_back) // Show back button
                    topAppBar.setNavigationOnClickListener { navController.navigateUp() } // Handle back navigation
                }
            }

            // Handle Navigation Drawer Item Clicks
            navigationView.setNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.nav_home -> navController.navigate(R.id.firstFragment)
                    R.id.nav_profile -> navController.navigate(R.id.secondFragment)
                    R.id.nav_settings -> navController.navigate(R.id.thirdFragment)
                    R.id.nav_logout -> {
                        Log.d("MainActivity", "Logout clicked")
                    }
                }
                drawerLayout.closeDrawers() // Close drawer after selection
                true
            }
        } else {
            Log.e("MainActivity", "NavController is null! Check nav_host_fragment")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Open drawer when menu button is clicked
        if (item.itemId == android.R.id.home) {
            drawerLayout.openDrawer(navigationView)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
