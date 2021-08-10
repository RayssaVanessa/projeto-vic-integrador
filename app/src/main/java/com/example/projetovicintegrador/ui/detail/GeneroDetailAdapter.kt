package com.example.projetovicintegrador.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projetovicintegrador.databinding.ItemSinopsBinding

class GeneroDetailAdapter(private val genreFilm: List<String>) :
    RecyclerView.Adapter<GeneroDetailAdapter.ViewHolder>() {

    class ViewHolder(private val bindGenre: ItemSinopsBinding) :
        RecyclerView.ViewHolder(bindGenre.root) {
        fun genre(itemGenreBinding: String) {
            bindGenre.itemActivity.text = itemGenreBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemSinopsBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.genre(genreFilm[position])

    }

    override fun getItemCount() = genreFilm.size

}