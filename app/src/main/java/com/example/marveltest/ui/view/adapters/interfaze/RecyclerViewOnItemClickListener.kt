package com.example.marveltest.ui.view.adapters.interfaze

interface RecyclerViewOnItemClickListener<in Model> {
    fun onClick(model: Model)
}