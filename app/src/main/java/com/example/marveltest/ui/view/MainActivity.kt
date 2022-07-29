package com.example.marveltest.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.MarvelCharacter
import com.example.marveltest.R
import com.example.marveltest.core.adapters.interfaze.RecyclerViewOnItemClickListener
import com.example.marveltest.databinding.ActivityMainBinding
import com.example.marveltest.ui.view.adapters.MarvelCharctersAdapter
import com.example.marveltest.ui.viewmodel.MarvelViewModel
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
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