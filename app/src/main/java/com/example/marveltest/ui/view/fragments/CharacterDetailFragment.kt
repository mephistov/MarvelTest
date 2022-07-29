package com.example.marveltest.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.marveltest.R
import com.example.marveltest.databinding.FragmentCharacterDetailBinding
import com.example.marveltest.ui.viewmodel.MarvelViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailBinding
    private val marvelViewModel: MarvelViewModel by viewModels()
    var actionBar: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCharacterDetailBinding.inflate(layoutInflater)

        actionBar = (activity as AppCompatActivity?)!!.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)

        arguments?.let {
            marvelViewModel.getCharacterById(it.getInt("charId"))

        }
        marvelViewModel.isLoading.observe(this, Observer {
            binding.progressBar2.visibility = if (it) View.VISIBLE else View.GONE
        })
        marvelViewModel.characterModel.observe(this, Observer {
            binding.textView.text = it.name
            if (it.description != "")
                binding.textView2.text = it.description
            Glide
                .with(requireContext())
                .load(it.thumnail)
                .placeholder(R.drawable.loading_marvel)
                .into(binding.imageView2)
        })
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }


}