package com.example.marveltest.ui.view.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marveltest.R
import com.example.marveltest.core.adapters.BaseViewHolder
import com.example.marveltest.databinding.RowChararcterBinding
import com.example.marveltest.domain.model.MarvelCharacter
import com.example.marveltest.core.adapters.interfaze.RecyclerViewOnItemClickListener

class MarvelCharctersAdapter(
    private val onItemClickListener: RecyclerViewOnItemClickListener<MarvelCharacter>? = null,
    private val contextAdapter: Context
) : RecyclerView.Adapter<MarvelViewHolder>() {

    var listCharacters = listOf<MarvelCharacter>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelViewHolder =
        MarvelViewHolder.create(parent).apply {
            setOnClickListener(onItemClickListener)
            context = contextAdapter
        }

    override fun onBindViewHolder(holder: MarvelViewHolder, position: Int) {
        holder.bindTo(listCharacters[position])
    }

    override fun getItemCount(): Int = listCharacters.size
}

class MarvelViewHolder(
    private val binding: RowChararcterBinding
    ): BaseViewHolder<MarvelCharacter>(binding.root), View.OnClickListener {

    lateinit var context:Context

    companion object {
        fun create(parent: ViewGroup): MarvelViewHolder {
            val binding = parent.viewBinding(RowChararcterBinding::inflate)
            return MarvelViewHolder(binding)
        }
    }

    init {
        itemView.setOnClickListener(this)
    }

    private lateinit var marvelCharacter: MarvelCharacter
    private var onItemClickListener: RecyclerViewOnItemClickListener<MarvelCharacter>? = null

    override fun bindTo(model: MarvelCharacter) {
        marvelCharacter = model
        binding.textViewName.text = model.name
        binding.textViewShortDescription.text = model.description

        Glide
            .with(context)
            .load(model.thumnail)
            .centerCrop()
            .placeholder(R.drawable.loading_marvel)
            .into(binding.imageView)

    }

    override fun onClick(p0: View?) {
        onItemClickListener?.onClick(marvelCharacter)
    }

    fun setOnClickListener(onItemClickListener: RecyclerViewOnItemClickListener<MarvelCharacter>?) {
        this.onItemClickListener = onItemClickListener
    }

}