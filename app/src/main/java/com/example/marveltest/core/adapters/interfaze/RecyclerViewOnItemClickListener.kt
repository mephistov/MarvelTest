package com.example.marveltest.core.adapters.interfaze

interface RecyclerViewOnItemClickListener<in Model> {
    fun onClick(model: Model)
}