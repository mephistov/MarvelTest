package com.example.marveltest.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marveltest.databinding.ActivityMainBinding
import com.example.marveltest.domain.model.MarvelCharacter
import com.example.marveltest.ui.view.adapters.MarvelCharctersAdapter
import com.example.marveltest.core.adapters.interfaze.RecyclerViewOnItemClickListener
import com.example.marveltest.ui.viewmodel.MarvelViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RecyclerViewOnItemClickListener<MarvelCharacter> {

    private lateinit var binding: ActivityMainBinding
    private val marvelViewModel: MarvelViewModel by viewModels()
    private lateinit var  adapter: MarvelCharctersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        marvelViewModel.getAllMarvelCharacters()

        marvelViewModel.charactersListModel.observe(this, Observer {
            binding.textViewNodata.visibility = View.GONE
            adapter = MarvelCharctersAdapter(this,baseContext)
            adapter.listCharacters = it
            binding.list.adapter = adapter
            binding.list.layoutManager = LinearLayoutManager(baseContext)
        })
        marvelViewModel.isLoading.observe(this, Observer {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    override fun onClick(model: MarvelCharacter) {
        val intent = Intent(this, CharacterDetailActivity::class.java)
        intent.putExtra("charId",model.id)
        startActivity(intent)
    }
}