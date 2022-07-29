package com.example.marveltest.ui.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.MarvelCharacter
import com.example.marveltest.R
import com.example.marveltest.core.adapters.interfaze.RecyclerViewOnItemClickListener
import com.example.marveltest.databinding.FragmentMarvelMainBinding
import com.example.marveltest.ui.view.adapters.MarvelCharctersAdapter
import com.example.marveltest.ui.viewmodel.MarvelViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarvelMainFragment : Fragment(R.layout.fragment_marvel_main),
    RecyclerViewOnItemClickListener<MarvelCharacter> {

    private lateinit var binding:FragmentMarvelMainBinding
    private val marvelViewModel: MarvelViewModel by viewModels()
    private lateinit var  adapter: MarvelCharctersAdapter
    private var listCharactersTotal :ArrayList<MarvelCharacter> = ArrayList()
    private var page = 0
    private val LIMIT = 20
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMarvelMainBinding.inflate(layoutInflater)
        navController = this.findNavController()

        marvelViewModel.getAllMarvelCharacters(page*LIMIT)

        marvelViewModel.charactersListModel.observe(this, Observer {listChar->
            if(listCharactersTotal.size == 0){
                binding.textViewNodata.visibility = View.GONE
                mContext?.let {
                    adapter = MarvelCharctersAdapter(this,it)
                    listCharactersTotal.addAll(listChar as ArrayList<MarvelCharacter>)
                    adapter.listCharacters = listCharactersTotal
                    binding.list.adapter = adapter
                    binding.list.layoutManager = LinearLayoutManager(getContext())
                }

            }else{
                listCharactersTotal.addAll(listChar as ArrayList<MarvelCharacter>)
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
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root

    }


    override fun onClick(model: MarvelCharacter) {
        val bundle = bundleOf("charId" to model.id)
        navController.navigate(R.id.characterDetailFragment,bundle)
    }

    private var mContext: Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }



}