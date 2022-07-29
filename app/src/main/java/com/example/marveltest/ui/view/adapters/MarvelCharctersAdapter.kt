package com.example.marveltest.ui.view.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.MarvelCharacter
import com.example.marveltest.core.RowOptions
import com.example.marveltest.core.adapters.BaseViewHolder
import com.example.marveltest.core.adapters.interfaze.RecyclerViewOnItemClickListener
import com.example.marveltest.databinding.RowChararcterBinding
import com.example.marveltest.databinding.RowFooterBinding

class MarvelCharctersAdapter(
    private val onItemClickListener: RecyclerViewOnItemClickListener<MarvelCharacter>? = null,
    private val contextAdapter: Context
) : RecyclerView.Adapter<BaseViewHolder<MarvelCharacter>>() {

    var listCharacters = listOf<MarvelCharacter>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MarvelCharacter>{

        if(viewType == RowOptions.FOOTER.opc){
            return FooterViewHolder.create(parent)
        }

        return MarvelViewHolder.create(parent).apply {
            setOnClickListener(onItemClickListener)
            context = contextAdapter
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<MarvelCharacter>, position: Int) {
        holder.bindTo(listCharacters[position])
    }

    override fun getItemCount(): Int = listCharacters.size

    override fun getItemViewType(position: Int): Int {
        if(position == listCharacters.size-1){
            return RowOptions.FOOTER.opc
        }

        return super.getItemViewType(position)

    }
}

class FooterViewHolder(
    private val binding:RowFooterBinding
):BaseViewHolder<MarvelCharacter>(binding.root){
    companion object {
        fun create(parent: ViewGroup): FooterViewHolder {
            val binding = parent.viewBinding(RowFooterBinding::inflate)
            return FooterViewHolder(binding)
        }
    }

    override fun bindTo(model: MarvelCharacter) {}
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
        binding.characterAdapter = model
        marvelCharacter = model

    }

    override fun onClick(p0: View?) {
        onItemClickListener?.onClick(marvelCharacter)
    }

    fun setOnClickListener(onItemClickListener: RecyclerViewOnItemClickListener<MarvelCharacter>?) {
        this.onItemClickListener = onItemClickListener
    }

}