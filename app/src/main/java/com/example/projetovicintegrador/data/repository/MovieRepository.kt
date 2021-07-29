package com.example.projetovicintegrador.data.repository

import com.example.projetovicintegrador.IMovieRepository
import com.example.projetovicintegrador.data.remote.MovieMapper
import com.example.projetovicintegrador.data.remote.MovieRemote
import com.example.projetovicintegrador.model.GenreReference
import com.example.projetovicintegrador.model.MovieReference
import com.example.projetovicintegrador.model.Resource
import javax.inject.Inject

//Separando as responsabilidades
class MovieRepository @Inject constructor(private val remote: MovieRemote) : IMovieRepository {
    override suspend fun getMovies(): Resource<Exception, List<MovieReference>> {
        //Tentar executar oq ta dentro
        // e se não der certo, executa o que tá dentro do catch
        return try {
            //chamando a lista de filmes
            val result = remote.getMovies()
            val list = MovieMapper.movieResponseToMovieReference(result)
            return Resource.build { list }
        } catch (e: Exception) {
            Resource.setError(e)
        }
    }

    override suspend fun getGenre(): Resource<Exception, List<GenreReference>> {
        return try {
            val result = remote.getGenres()
            val list = MovieMapper.genreResponseToGenreReference(result)
            return Resource.build { list }
        } catch (e: Exception) {
            Resource.setError(e)
        }
    }

}