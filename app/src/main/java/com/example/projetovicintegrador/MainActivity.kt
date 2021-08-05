package com.example.projetovicintegrador

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
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
                changeStyleQuantityMovies(state.movies.size)
                updateList(state.movies)
            }

            is MainState.LoadGenres -> {
                updateListGenre(state.genres)
            }

            is Exception -> {
                    binding.containerMovies.isVisible = false
                    binding.includeLayoutError.root.isVisible = true
            }
        }
    }

    private fun changeStyleQuantityMovies(quantityMovies: Int) {
            if(quantityMovies > 0) {
                binding.rvFilm.visibility = View.VISIBLE
                binding.containerNoMovies.isVisible = false
            } else {
                binding.rvFilm.visibility = View.INVISIBLE
                binding.containerNoMovies.isVisible = true
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
            filterByGenres(it)
        }
        binding.RvGenre.adapter = genreAdapter
    }

    private fun configViews() {
        binding.apply {
            btnPesquisa.setOnClickListener{ initSearch() }
            textTodosFilmes.setOnClickListener {
                changeStyleForAllMovies()
            }
            textFavoritos.setOnClickListener {
                changeStyleForFavoriteMovies()
            }
            campoPesquisa.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    changeStyleForSearchMovie()
                }
            }

            campoPesquisa.setOnClickListener {
                changeStyleForSearchMovie()
            }

            voltarInicio.setOnClickListener{
                changeStyleForNoSearchMovie()
                if (containerNoMovies.isVisible) {
                    viewModel.getMovies()
                }
            }

            includeLayoutError.tentarNovamente.setOnClickListener{
                includeLayoutError.root.isVisible = false
                containerMovies.isVisible = true
                viewModel.getMovies()
            }

            includeLayoutError.icClose.setOnClickListener {
                finish()
            }
        }

    }
    private fun limparTeclado(){
        val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm.isActive()) imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }

    private fun changeStyleForSearchMovie() {
        binding.apply {
            containerOptions.isVisible = false
            containerSearch.isVisible = true
        }
    }

    private fun changeStyleForNoSearchMovie() {
        binding.apply {
            containerOptions.isVisible = true
            containerSearch.isVisible = false
            campoPesquisa.text.clear()
            limparTeclado()
        }
    }

    private fun initSearch() {
        viewModel.getSearchMovies(binding.campoPesquisa.text.toString())
    }

    private fun filterByGenres(genres: List<Int>) {
        viewModel.filterByGenres(genres, binding.containerSearch.isVisible)
    }

}