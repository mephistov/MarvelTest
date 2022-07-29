package com.example.marveltest.ui.view

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.example.marveltest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    var  actionBar: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = getSupportActionBar()
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.setHomeButtonEnabled(false)

    }

    private fun disableMenuOption(){
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.setHomeButtonEnabled(false)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        disableMenuOption()
    }

    override
    fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}