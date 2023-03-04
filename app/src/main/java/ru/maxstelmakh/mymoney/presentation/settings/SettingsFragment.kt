package ru.maxstelmakh.mymoney.presentation.settings

import android.content.ComponentName
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import ru.maxstelmakh.mymoney.R
import ru.maxstelmakh.mymoney.presentation.splashscreen.SplashActivity

class SettingsFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        PreferenceManager.getDefaultSharedPreferences(requireContext())
            .registerOnSharedPreferenceChangeListener(this)
    }


    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == "dark_mode") {
            val prefs = sharedPreferences?.getString(key, "1")
            when (prefs?.toInt()) {
                1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                2 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                3 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                4 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)

            }
        }
        if (key == "icon_choice") {
            val prefs = sharedPreferences?.getString(key, "1")
            when (prefs?.toInt()) {
                1 -> setClassicIcon()
                2 -> setEmeraldIcon()
                3 -> setPurpleIcon()
                4 -> setGreenIcon()

            }
        }
    }

    private fun setClassicIcon() {
        val packManager = requireActivity().packageManager
        packManager.setComponentEnabledSetting(
            ComponentName(
                requireActivity().baseContext, SplashActivity::class.java
            ),
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )

        packManager?.setComponentEnabledSetting(
            ComponentName(
                requireActivity().baseContext, "presentation.second"
            ),
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )


        packManager?.setComponentEnabledSetting(
            ComponentName(
                requireActivity().baseContext, "presentation.third"
            ),
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )

        packManager?.setComponentEnabledSetting(
            ComponentName(
                requireActivity().baseContext, "presentation.four"
            ),
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )
    }

    private fun setEmeraldIcon() {
        val packManager = requireActivity().packageManager
        packManager.setComponentEnabledSetting(
            ComponentName(
                requireActivity().baseContext, SplashActivity::class.java
            ),
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )

        packManager.setComponentEnabledSetting(
            ComponentName(
                requireActivity().baseContext, "presentation.second"
            ),
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )

        packManager?.setComponentEnabledSetting(
            ComponentName(
                requireActivity().baseContext, "presentation.third"
            ),
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )

        packManager?.setComponentEnabledSetting(
            ComponentName(
                requireActivity().baseContext, "presentation.four"
            ),
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )
    }

    private fun setPurpleIcon() {
        val packManager = requireActivity().packageManager
        packManager.setComponentEnabledSetting(
            ComponentName(
                requireActivity().baseContext, SplashActivity::class.java
            ),
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )

        packManager.setComponentEnabledSetting(
            ComponentName(
                requireActivity().baseContext, "presentation.second"
            ),
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )

        packManager?.setComponentEnabledSetting(
            ComponentName(
                requireActivity().baseContext, "presentation.third"
            ),
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )

        packManager?.setComponentEnabledSetting(
            ComponentName(
                requireActivity().baseContext, "presentation.four"
            ),
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )
    }

    private fun setGreenIcon() {
        val packManager = requireActivity().packageManager
        packManager.setComponentEnabledSetting(
            ComponentName(
                requireActivity().baseContext, SplashActivity::class.java
            ),
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )

        packManager.setComponentEnabledSetting(
            ComponentName(
                requireActivity().baseContext, "presentation.second"
            ),
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )

        packManager?.setComponentEnabledSetting(
            ComponentName(
                requireActivity().baseContext, "presentation.third"
            ),
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )

        packManager?.setComponentEnabledSetting(
            ComponentName(
                requireActivity().baseContext, "presentation.four"
            ),
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        PreferenceManager.getDefaultSharedPreferences(requireContext())
            .unregisterOnSharedPreferenceChangeListener(this)
    }
}