package com.example.projetovicintegrador.data.di

import com.example.projetovicintegrador.data.remote.MovieRemote
import com.example.projetovicintegrador.data.remote.api.MovieApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideMovieApi(): MovieApi {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(OkHttpClient.Builder().build())
            .build()
            .create(MovieApi::class.java)
    }

    @Singleton //Só uma instancia
    @Provides // to provendo o retorno da funçao
    fun provideMovieRemote(): MovieRemote = MovieRemote(provideMovieApi())
}