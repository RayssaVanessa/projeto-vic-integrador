package com.example.projetovicintegrador

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projetovicintegrador.databinding.ItemElencoBinding
import com.example.projetovicintegrador.model.Elenco

class ElencoAdapter(private val listaElenco: List<Elenco>) :
    RecyclerView.Adapter<ElencoAdapter.MyViewHolder>() {

    class MyViewHolder(private val bindingComponent: ItemElencoBinding) :
        RecyclerView.ViewHolder(bindingComponent.root) {
        fun bind(elenco: Elenco) {
            //Atribuindo o valor do meu objeto elenco ao componente do XML
            bindingComponent.funcao.text = elenco.profission
            bindingComponent.nomeElenco.text = elenco.nameAuthor
            Glide.with(bindingComponent.itemElenco.context).load(elenco.photo)
                .into(bindingComponent.itemElenco)

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



