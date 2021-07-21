package com.example.projetovicintegrador

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projetovicintegrador.databinding.ItemElencoBinding
import com.example.projetovicintegrador.model.Elenco

class ElencoAdapter(private val listaElenco: List<Elenco>) :
    RecyclerView.Adapter<ElencoAdapter.MyViewHolder>() {

    class MyViewHolder(private val bindingComponent: ItemElencoBinding) :
        RecyclerView.ViewHolder(bindingComponent.root) {
        fun bind(elenco: Elenco) {
            bindingComponent.funcao.text = elenco.profission
            bindingComponent.nomeElenco.text = elenco.nameAuthor

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemElencoBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listaElenco[position])
    }

    override fun getItemCount() = listaElenco.size
}



