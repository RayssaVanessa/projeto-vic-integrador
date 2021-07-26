package com.example.projetovicintegrador

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projetovicintegrador.databinding.ActivityMainBinding
import com.example.projetovicintegrador.model.Filme
import com.example.projetovicintegrador.model.MovieReference

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val intent = Intent(this, SynopsisActivity::class.java)
//        startActivity(intent)

        //Criando uma lista com  3 filmes iguais
        val adapterGenre = AdapterGenActivity(listGenActivity = listOf("Ação", "Romance", "Drama"))
        val listFilm = createMoviesFake()
        val adapterFilm = FilmeAdapter(listFilm)
        binding.rvFilm.adapter = adapterFilm
        binding.RvGenre.adapter = adapterGenre

    }

    fun createMoviesFake(): List<MovieReference> {
        //Criar pelo menos 3 filmes, add numa lista e retornar nessa lista p ver na main
        val filme1 = Filme(
            nameFilm =""

        )
    }
}