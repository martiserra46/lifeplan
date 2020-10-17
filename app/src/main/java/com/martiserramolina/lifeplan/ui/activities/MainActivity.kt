package com.martiserramolina.lifeplan.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.martiserramolina.lifeplan.R

class MainActivity : AppCompatActivity() {

    val navController: NavController
        get() = supportFragmentManager.findFragmentById(
            R.id.activity_main_fcv
        ).run { this as NavHostFragment }.navController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}