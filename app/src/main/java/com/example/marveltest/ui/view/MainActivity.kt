package com.example.marveltest.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.MarvelCharacter
import com.example.marveltest.core.adapters.interfaze.RecyclerViewOnItemClickListener
import com.example.marveltest.databinding.ActivityMainBinding
import com.example.marveltest.ui.view.adapters.MarvelCharctersAdapter
import com.example.marveltest.ui.viewmodel.MarvelViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RecyclerViewOnItemClickListener<MarvelCharacter> {

    private lateinit var binding: ActivityMainBinding
    private val marvelViewModel: MarvelViewModel by viewModels()
    private lateinit var  adapter: MarvelCharctersAdapter
    private var listCharactersTotal :ArrayList<MarvelCharacter> = ArrayList()
    private var page = 0
    private val LIMIT = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        marvelViewModel.getAllMarvelCharacters(page*LIMIT)

        marvelViewModel.charactersListModel.observe(this, Observer {
            if(listCharactersTotal.size == 0){
                binding.textViewNodata.visibility = View.GONE
                adapter = MarvelCharctersAdapter(this,baseContext)
                listCharactersTotal.addAll(it as ArrayList<MarvelCharacter>)
                adapter.listCharacters = listCharactersTotal
                binding.list.adapter = adapter
                binding.list.layoutManager = LinearLayoutManager(baseContext)
            }else{
                listCharactersTotal.addAll(it as ArrayList<MarvelCharacter>)
                adapter.listCharacters = listCharactersTotal
            }
            page++

        })
        marvelViewModel.isLoading.observe(this, Observer {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        binding.list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(!recyclerView.canScrollVertically(1)) {
                    marvelViewModel.getAllMarvelCharacters(page*LIMIT)
                }
            }
        })
    }

    override fun onClick(model: MarvelCharacter) {
        val intent = Intent(this, CharacterDetailActivity::class.java)
        intent.putExtra("charId",model.id)
        startActivity(intent)
    }
}