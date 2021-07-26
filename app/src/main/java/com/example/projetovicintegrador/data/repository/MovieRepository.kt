package com.example.projetovicintegrador.data.repository

import com.example.projetovicintegrador.data.remote.MovieMapper
import com.example.projetovicintegrador.data.remote.MovieRemote
import com.example.projetovicintegrador.model.MovieReference
import com.example.projetovicintegrador.model.Resource

//Separando as responsabilidades
class MovieRepository(private val remote: MovieRemote) {
    suspend fun getMovies(): Resource<Exception, List<MovieReference>> {
        //Tentar executar oq ta dentro
        // e se não der certo, executa o que tá dentro do cath
        return try {
            //chamando a lista de filmes
            val result = remote.getMovies()
            val list = MovieMapper.movieResponseToMovieReference(result)
            return Resource.build { list }
        } catch (e: Exception) {
            Resource.setError(e)
        }
    }

}