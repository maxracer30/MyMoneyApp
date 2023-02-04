package ru.maxstelmakh.mymoney.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import ru.maxstelmakh.mymoney.R
import ru.maxstelmakh.mymoney.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navController = findNavController(R.id.fragmentContainerView)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.mainFragment,
            R.id.plansFragment,
            R.id.splashFragment
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.mainBottomNavigationView.setupWithNavController(navController)
    }
}