package com.example.marveltest.ui.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.marveltest.R
import com.example.marveltest.databinding.ActivityCharacterDetailBinding
import com.example.marveltest.ui.view.adapters.MarvelCharctersAdapter
import com.example.marveltest.ui.viewmodel.MarvelViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var binding:ActivityCharacterDetailBinding
    private val marvelViewModel: MarvelViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = getSupportActionBar()
        actionBar?.setDisplayHomeAsUpEnabled(true);

        val idCharacter = intent.getIntExtra("charId",0)
        marvelViewModel.getCharacterById(idCharacter)

        marvelViewModel.isLoading.observe(this, Observer {
            binding.progressBar2.visibility = if (it) View.VISIBLE else View.GONE
        })
        marvelViewModel.characterModel.observe(this, Observer {
            binding.textView.text = it.name
            if (it.description != "")
                binding.textView2.text = it.description
            Glide
                .with(baseContext)
                .load(it.thumnail)
                .placeholder(R.drawable.loading_marvel)
                .into(binding.imageView2)
        })

    }

    override
    fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}