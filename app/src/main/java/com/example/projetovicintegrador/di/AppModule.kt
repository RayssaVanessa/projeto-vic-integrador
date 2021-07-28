package com.example.projetovicintegrador.di

import com.example.projetovicintegrador.IMovieRepository
import com.example.projetovicintegrador.data.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindMovieRepository(movieRepository: MovieRepository): IMovieRepository
}