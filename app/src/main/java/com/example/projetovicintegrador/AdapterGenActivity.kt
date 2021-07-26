package com.example.projetovicintegrador

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projetovicintegrador.databinding.ItemGenreBinding

class AdapterGenActivity(private val listGenActivity: List<String>) :
    RecyclerView.Adapter<AdapterGenActivity.ViewHolderGenActivity>() {

    class ViewHolderGenActivity(private val bindGen: ItemGenreBinding) :
        RecyclerView.ViewHolder(bindGen.root) {
        fun bind(itemGenreBinding: String) {
            bindGen.genreActivity.text = itemGenreBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderGenActivity {
        return ViewHolderGenActivity(ItemGenreBinding.inflate(LayoutInflater.from(parent.context),
            parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolderGenActivity, position: Int) {
        holder.bind(listGenActivity[position])
    }

    override fun getItemCount() = listGenActivity.size
}