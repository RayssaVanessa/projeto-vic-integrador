package com.example.projetovicintegrador

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projetovicintegrador.databinding.ItemGenreBinding
import com.example.projetovicintegrador.databinding.ItemSinopsBinding
import com.example.projetovicintegrador.model.GenreReference
import com.example.projetovicintegrador.model.MovieReference

class GenreAdapter(
    private val genreFilm: List<GenreReference>,
    val onClickGenre: (GenreReference) -> Unit,
) : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    inner class ViewHolder(private val bindGenre:ItemGenreBinding) :
        RecyclerView.ViewHolder(bindGenre.root) {
        fun genre(itemGenreBinding: GenreReference) {
            bindGenre.apply {
                itemGenre.text = itemGenreBinding.name
                root.setOnClickListener {
                    onClickGenre.invoke(itemGenreBinding)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemGenreBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.genre(genreFilm[position])

    }

    override fun getItemCount() = genreFilm.size

}