package com.martiserramolina.lifeplan.ui.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.martiserramolina.lifeplan.InstructionsNestedNavGraphDirections
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.ui.fragments.main.MainFragmentDirections

class MainActivity : AppCompatActivity() {

    val navController: NavController
        get() = supportFragmentManager.findFragmentById(
            R.id.activity_main_fcv
        ).run { this as NavHostFragment }.navController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupFirstTimeAppLaunchedFunctionality()
    }

    private fun setupFirstTimeAppLaunchedFunctionality() {
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        val appLaunchedBeforeKey = getString(R.string.preference_key_app_launched_before)
        val appLaunchedBefore = sharedPreferences.getBoolean(appLaunchedBeforeKey, false)
        if (appLaunchedBefore) return
        navController.navigate(
            InstructionsNestedNavGraphDirections
                .actionGlobalInstructionsNestedNavGraph(false)
        )
        sharedPreferences.edit().putBoolean(appLaunchedBeforeKey, true).apply()
    }
}