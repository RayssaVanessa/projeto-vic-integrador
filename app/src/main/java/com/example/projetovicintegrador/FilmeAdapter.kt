package com.example.projetovicintegrador

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projetovicintegrador.databinding.ItemFilmeBinding
import com.example.projetovicintegrador.model.MovieReference

class FilmeAdapter(
    private val films: List<MovieReference>,
    val onClickMovie: (MovieReference) -> Unit,
) : RecyclerView.Adapter<FilmeAdapter.ViewHolder>() {

//classe view holder pertence a clase filme adapter
    inner class ViewHolder(private val filmeComponente: ItemFilmeBinding) :
        RecyclerView.ViewHolder(filmeComponente.root) {
        fun bind(filme: MovieReference) {
            filmeComponente.apply {
                rating.text = filme.voteAverage
                textView2.text = filme.title
                Glide.with(imageView.context).load(filme.posterPath).into(imageView)
                root.setOnClickListener {
                    onClickMovie.invoke(filme)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemFilmeBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(films[position])

    }

    override fun getItemCount() = films.size

}