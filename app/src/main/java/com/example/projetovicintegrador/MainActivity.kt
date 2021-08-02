package com.example.projetovicintegrador

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.projetovicintegrador.SynopsisActivity.Companion.ID_ARG
import com.example.projetovicintegrador.databinding.ActivityMainBinding
import com.example.projetovicintegrador.model.GenreReference
import com.example.projetovicintegrador.model.MovieReference
import dagger.hilt.android.AndroidEntryPoint
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
        val movieAdapter = FilmeAdapter(movies, {
            openDetailMovie(it)
        })
        binding.rvFilm.adapter = movieAdapter
    }

    private fun openDetailMovie(movieReference: MovieReference) {
        val intent = Intent(this, SynopsisActivity::class.java).apply {
            putExtra(ID_ARG, movieReference.id)
        }
        startActivity(intent)
    }

    private fun updateListGenre(genres: List<GenreReference>) {
        val genreAdapter = GenreAdapter(genres, {
            openDetailGenre(it)
        })
        binding.RvGenre.adapter = genreAdapter
    }

    private fun openDetailGenre(genres: GenreReference) {

    }

}