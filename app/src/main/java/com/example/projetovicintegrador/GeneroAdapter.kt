package com.example.projetovicintegrador

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.projetovicintegrador.databinding.ItemGenreBinding
import com.example.projetovicintegrador.model.GenreReference

class GenreAdapter(
    private val genreFilm: List<GenreReference>,
    private val onClickGenre: (ArrayList<Int>) -> Unit,
) : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    private val selectedGenresId = arrayListOf<Int>()

    inner class ViewHolder(private val bindGenre: ItemGenreBinding) :
        RecyclerView.ViewHolder(bindGenre.root) {
        fun genre(itemGenreBinding: GenreReference, position: Int) {
            bindGenre.apply {
                itemGenre.text = itemGenreBinding.name
                root.setOnClickListener {
                    updateListGenres(itemGenreBinding.id, position)
                    onClickGenre.invoke(selectedGenresId)
                }
                updateView(this,itemGenreBinding.id)
            }
        }
    }

    private fun updateView(bindGenre: ItemGenreBinding, id: Int) {
        bindGenre.apply {
            val context = this.itemGenre.context
            if (selectedGenresId.contains(id)) {
                this.cvItemGenre.backgroundTintList =
                    ContextCompat.getColorStateList(context, R.color.genre_selected)
                this.itemGenre.setTextColor(ContextCompat.getColor(context,
                    R.color.white))
            } else {
                this.cvItemGenre.backgroundTintList =
                    ContextCompat.getColorStateList(context, R.color.buttonGenreColor)
                this.itemGenre.setTextColor(ContextCompat.getColor(context,
                    R.color.black))
            }
        }
    }

    private fun updateListGenres(id: Int, position: Int) {
        if (selectedGenresId.contains(id)) {
            selectedGenresId.remove(id)
        } else {
            selectedGenresId.add(id)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemGenreBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.genre(genreFilm[position], position)

    }

    override fun getItemCount() = genreFilm.size
}