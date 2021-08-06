package com.example.projetovicintegrador

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.projetovicintegrador.databinding.SinopseBinding
import com.example.projetovicintegrador.model.Filme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SynopsisActivity : AppCompatActivity() {

    companion object {
        const val ID_ARG = "id_arg"
    }

    private val viewModel: DetailViewModel by viewModels()


    // declarando xml numa classe
    private lateinit var binding: SinopseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // instanciando meu objeto da classe
        binding = SinopseBinding.inflate(layoutInflater)
        setContentView(binding.root) // mesma coisa de estar passando o layout xml
        binding.cardView.setBackgroundResource(R.drawable.bg_left_rounded)

        viewModel.state.observe(this, { handleState(it) })

        intent.extras?.getLong(ID_ARG)?.let {
            viewModel.getDetail(it)
        }
    }

    private fun handleState(state: Any) {
        if (state is DetailState.LoadDetail) {
            configViews(state.detail)
        }
    }

    private fun configViews(movie: Filme) {
        // populando view com valores do objeto
        binding.titleFilm.text = movie.title
        val adapterElenco = ElencoAdapter(movie.listElenco)
        binding.apply {
            year.text = movie.year.toString()
            pg.text = movie.pg
            duration.text = movie.time
            synopsisDescricion.text = movie.biography
            rating.text = movie.rate
            listElenco.adapter = adapterElenco
            listGenre.adapter = GeneroDetailAdapter(movie.genre)
            Glide.with(this@SynopsisActivity).load(movie.poster).into(imageSynopsis)
            imageView8.setOnClickListener {
                viewModel.changeFavorite(movie)
            }
        }
        //Vou popular os campos do xml
    }
}


