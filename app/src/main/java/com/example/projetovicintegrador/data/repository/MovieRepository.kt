package com.example.projetovicintegrador.data.repository

import com.example.projetovicintegrador.AppApplication
import com.example.projetovicintegrador.IMovieRepository
import com.example.projetovicintegrador.data.remote.MovieMapper
import com.example.projetovicintegrador.data.remote.MovieRemote
import com.example.projetovicintegrador.model.Filme
import com.example.projetovicintegrador.model.GenreReference
import com.example.projetovicintegrador.model.MovieReference
import com.example.projetovicintegrador.model.Resource
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject

//Separando as responsabilidades
class MovieRepository @Inject constructor(private val remote: MovieRemote) : IMovieRepository {
    // maneira de salvar uma lista no sharedpreference
    private val savedList: Type = object : TypeToken<List<MovieReference>>() {}.type
    private val gson = GsonBuilder().create()

    companion object {
        private const val MOVIES = "movies_referencies"
        private const val EMPTY = ""
        private const val NAME = "Vic"
        private const val MODE_PRIVATE = 0
    }

    override suspend fun getMovies(): Resource<Exception, List<MovieReference>> {
        //Tentar executar oq ta dentro
        // e se não der certo, executa o que tá dentro do catch
        return try {
            //chamando a lista de filmes
            val result = remote.getMovies()
            val list = MovieMapper.movieResponseToMovieReference(result)
            list.forEach { it.isFavorite = isFavorite(it.id) }
            return Resource.build { list }
        } catch (e: Exception) {
            Resource.setError(e)
        }
    }

    override suspend fun getSearchMovies(title: String): Resource<Exception, List<MovieReference>> {
        return try {
            //chamando a lista de filmes
            val result = remote.getSearchMovie(title)
            val list = MovieMapper.searchMovieResponseToMovieReference(result)
            list.forEach { it.isFavorite = isFavorite(it.id) }
            return Resource.build { list }
        } catch (e: Exception) {
            Resource.setError(e)
        }
    }

    override suspend fun getMoviesByGenres(ids: List<Int>): Resource<Exception, List<MovieReference>> {
        return try {
            val result = remote.getMoviesByGenres(ids)
            val list = MovieMapper.searchMovieResponseToMovieReference(result)
            list.forEach { it.isFavorite = isFavorite(it.id) }
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

    override suspend fun getDetail(id: Long): Resource<Exception, Filme> {
        return try {
            val detailResult = remote.getDetail(id)
            val castResult = remote.getCast(id)
            val pgResult = remote.getPgMovies(id)
            val filme = MovieMapper.createFilme(detailResult, castResult, pgResult)
            filme.isFavorite = isFavorite(filme.id)
            return Resource.build { filme }
        } catch (e: Exception) {
            Resource.setError(e)
        }
    }

    override suspend fun changeFavoriteReferenceMovie(movieReference: MovieReference): Resource<Exception, Unit> {
        updateFavoriteList(movieReference)
        return Resource.build { }
    }

    override suspend fun getFavoriteMovie(): Resource<Exception, List<MovieReference>> {
        return Resource.build { getSavedList() }
    }

    override suspend fun changeFavoriteMovie(movie: Filme): Resource<Exception, Unit> {
        updateFavoriteList(MovieReference(movie.poster,
            movie.id,
            movie.title,
            movie.rate,
            movie.genre.map { it.id }))
        return Resource.build { }
    }

    private fun getSavedList(): ArrayList<MovieReference> {
        val sharedPreference = AppApplication.staticContex.getSharedPreferences(NAME, MODE_PRIVATE)
        var savedMovies: ArrayList<MovieReference>? = gson.fromJson<ArrayList<MovieReference>>(
            sharedPreference.getString(MOVIES, EMPTY),
            savedList
        )

        if (savedMovies == null) {
            savedMovies = arrayListOf()
        }

        return savedMovies
    }

    private fun updateFavoriteList(movieReference: MovieReference) {
        val sharedPreference = AppApplication.staticContex.getSharedPreferences(NAME, MODE_PRIVATE)
        val savedMovies = getSavedList()
        val movieSaved = savedMovies.firstOrNull { it.id == movieReference.id }

        if (movieSaved == null) {
            savedMovies.add(movieReference)
        } else {
            savedMovies.remove(movieSaved)
        }

        val json = gson.toJson(savedMovies)
        sharedPreference.edit().putString(MOVIES, json).apply()
    }

    private fun isFavorite(id: Long): Boolean {
        val favorites = getSavedList()
        return favorites.firstOrNull { it.id == id } != null
    }

}