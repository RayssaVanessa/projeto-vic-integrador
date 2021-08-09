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
    val onClickFavoriteMovie: (MovieReference) -> Unit,
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
                icFavorite.setOnClickListener {
                    onClickFavoriteMovie.invoke(filme)
                    filme.isFavorite = !filme.isFavorite
                    if (filme.isFavorite) {
                        icFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                    } else {
                        icFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    }
                }
                if (filme.isFavorite) {
                    icFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                } else {
                    icFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
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