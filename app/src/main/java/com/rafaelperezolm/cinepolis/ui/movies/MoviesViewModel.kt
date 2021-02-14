package com.rafaelperezolm.cinepolis.ui.movies

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.rafaelperezolm.cinepolis.data.repository.CinepolisRepository

class MoviesViewModel @ViewModelInject constructor(private val repository: CinepolisRepository) : ViewModel() {

    val movies = repository.getMovies()

}
