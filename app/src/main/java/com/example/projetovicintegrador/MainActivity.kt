package com.example.projetovicintegrador

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.projetovicintegrador.SynopsisActivity.Companion.ID_ARG
import com.example.projetovicintegrador.databinding.ActivityMainBinding
import com.example.projetovicintegrador.model.GenreReference
import com.example.projetovicintegrador.model.MovieReference
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.sinopse.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.apply {
            //observar oq vem da api(state)
            state.observe(this@MainActivity, { handleState(it) })
            getGenres()
            getMovies()
        }
        configViews()

        binding.textTodosFilmes.setOnClickListener {
        changeStyleForAllMovies()
        }
        binding.textFavoritos.setOnClickListener {
            changeStyleForFavoriteMovies()
        }
    }
    //alterar a visibilidade e o estilo do layout
    private fun changeStyleForAllMovies(){
        binding.apply {
            view.visibility = View.VISIBLE
            textTodosFilmes.setTextColor(ContextCompat.getColor(this@MainActivity,
                R.color.black))
            viewBotonFavorite.visibility = View.INVISIBLE
            textFavoritos.setTextColor(ContextCompat.getColor(this@MainActivity,
                R.color.gray))
        }
    }
    private fun changeStyleForFavoriteMovies(){
        binding.apply {
            view.visibility = View.INVISIBLE
            textTodosFilmes.setTextColor(ContextCompat.getColor(this@MainActivity,
                R.color.gray))
            viewBotonFavorite.visibility = View.VISIBLE
            textFavoritos.setTextColor(ContextCompat.getColor(this@MainActivity,
                R.color.black))
        }
    }

    private fun handleState(state: Any) {
        when (state) {
            is MainState.LoadMovies -> {
                updateList(state.movies)
            }

            is MainState.LoadGenres -> {
                updateListGenre(state.genres)
            }
        }
    }

    private fun updateList(movies: List<MovieReference>) {
        val movieAdapter = FilmeAdapter(movies) {
            openDetailMovie(it)
        }
        binding.rvFilm.adapter = movieAdapter
    }

    private fun openDetailMovie(movieReference: MovieReference) {
        val intent = Intent(this, SynopsisActivity::class.java).apply {
            putExtra(ID_ARG, movieReference.id)
        }
        startActivity(intent)
    }

    private fun updateListGenre(genres: List<GenreReference>) {
        val genreAdapter = GenreAdapter(genres) {
            filterGenres(it)
        }
        binding.RvGenre.adapter = genreAdapter
    }

    private fun configViews() {
        binding.apply {
            btnPesquisa.setOnClickListener{ initSearch() }
        }
    }

    private fun filterGenres(genres: List<Int>) {
        viewModel.getMoviesByGenres(genres)
    }

    private fun initSearch() {
        viewModel.getSearchMovies(binding.campoPesquisa.text.toString())
    }




}