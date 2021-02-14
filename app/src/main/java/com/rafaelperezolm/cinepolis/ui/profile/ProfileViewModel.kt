package com.rafaelperezolm.cinepolis.ui.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.rafaelperezolm.cinepolis.data.entities.Loginfo
import com.rafaelperezolm.cinepolis.data.entities.Profile
import com.rafaelperezolm.cinepolis.data.repository.CinepolisRepository
import com.rafaelperezolm.cinepolis.utils.Resource

class ProfileViewModel @ViewModelInject constructor(private val repository: CinepolisRepository) : ViewModel() {

    private val _token = MutableLiveData<String>()

    private val _profile = _token.switchMap { token ->
        repository.getProfile(token)
    }

    val profile: LiveData<Resource<List<Profile>>> = _profile

    fun start(token: String) {
        _token.value = token
    }

}