package ru.maxstelmakh.mymoney.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import ru.maxstelmakh.mymoney.R
import ru.maxstelmakh.mymoney.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        val navController = findNavController(R.id.fragmentContainerView)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.mainFragment,
                R.id.plansFragment,
                R.id.categoriesFragment,
                R.id.settingsFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

//        binding.apply {
//            mainBottomNavigationView.setupWithNavController(navController)
//            btnAdd.setOnClickListener {
//                supportFragmentManager.beginTransaction().replace(
//                    R.id.fragmentContainerView,
//
//                ).commit()
//            }
//        }

    }
}