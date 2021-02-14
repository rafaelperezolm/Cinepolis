package com.rafaelperezolm.cinepolis.ui.movies

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.rafaelperezolm.cinepolis.data.entities.Movy
import com.rafaelperezolm.cinepolis.data.repository.CinepolisRepository
import com.rafaelperezolm.cinepolis.utils.Resource

class MovieDetailViewModel @ViewModelInject constructor(private val repository: CinepolisRepository) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _movie = _id.switchMap { id ->
        repository.getMovie(id)
    }

    val movie: LiveData<Movy> = _movie

    fun start(id: Int) {
        _id.value = id
    }

}
