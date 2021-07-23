package com.example.projetovicintegrador

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projetovicintegrador.databinding.ItemSinopsBinding

class GenreAdapter(private val genreFilm: List<String>) :
    RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    class ViewHolder(private val bindGenre: ItemSinopsBinding) :
        RecyclerView.ViewHolder(bindGenre.root) {
        fun genre(itemGenreBinding: String) {
            bindGenre.itemGenre.text = itemGenreBinding
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