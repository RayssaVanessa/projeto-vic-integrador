package com.example.projetovicintegrador

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.projetovicintegrador.databinding.ActivityMainBinding
import com.example.projetovicintegrador.model.MovieReference
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.apply {
            state.observe(this@MainActivity, { handleState(it) })
        }

//        val intent = Intent(this, SynopsisActivity::class.java)
//        startActivity(intent)

        //Criando uma lista com  3 filmes iguais
        val adapterGenre = AdapterGenActivity(listGenActivity = listOf("Ação",
            "Romance",
            "Drama",
            "Comédia",
            "Terror"))

        binding.RvGenre.adapter = adapterGenre

    }

    private fun handleState(state: Any) {
        when (state) {
            is MainState.LoadMovies -> {
                updateList(state.movies)
            }
        }
    }

    private fun updateList(movies: List<MovieReference>) {
        val movieAdapter = FilmeAdapter(movies)
        binding.rvFilm.adapter = movieAdapter
    }

    fun createMoviesFake(): List<MovieReference> {
        //Criar pelo menos 3 filmes, add numa lista e retornar nessa lista p ver na main
        val filme1 =
            MovieReference(posterPath = "https://br.web.img2.acsta.net/pictures/21/04/07/21/13/5564614.jpg",
                id = 1989,
                title = "Cruella",
                voteAverage = "5.0%",
                listOf())
        val filme2 =
            MovieReference(posterPath = "https://cdn.awsli.com.br/1000x1000/1610/1610163/produto/68784203/poster-pantera-negra-c-ede2d387.jpg",
                id = 1233,
                title = "Pantera negra",
                voteAverage = "8.0%",
                listOf())
        val filme3 =
            MovieReference(posterPath = "https://img.elo7.com.br/product/zoom/2C013E0/big-poster-filme-star-wars-os-ultimos-jedi-tamanho-90x60-cm-presente-nerd.jpg",
                id = 9874,
                title = "Star wars",
                voteAverage = "4.9f",
                listOf())

        val listFilme = listOf(filme1, filme2, filme3)

        return listFilme
    }
}