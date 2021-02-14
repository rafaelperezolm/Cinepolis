package com.rafaelperezolm.cinepolis.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.rafaelperezolm.cinepolis.data.entities.Loginfo
import com.rafaelperezolm.cinepolis.data.entities.Movy
import com.rafaelperezolm.cinepolis.data.repository.CinepolisRepository
import com.rafaelperezolm.cinepolis.utils.Resource

class LoginEmailViewModel @ViewModelInject constructor(private val repository: CinepolisRepository) : ViewModel() {

    private val _credentials = MutableLiveData<String>()

    private val _loginfo = _credentials.switchMap { credentials ->
        repository.getLoginfo(credentials.substring(0, credentials.indexOf('|')), credentials.substring(credentials.indexOf('|')+1))
    }

    val loginfo: LiveData<Resource<List<Loginfo>>> = _loginfo

    fun start(credentials: String) {
        _credentials.value = credentials
    }

}