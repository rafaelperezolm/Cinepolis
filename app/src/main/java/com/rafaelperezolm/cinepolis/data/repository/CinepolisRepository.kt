package com.rafaelperezolm.cinepolis.data.repository

import com.rafaelperezolm.cinepolis.data.local.CinepolisDAO
import com.rafaelperezolm.cinepolis.data.remote.CinepolisRemoteDataSource
import com.rafaelperezolm.cinepolis.utils.performGetOperation
import javax.inject.Inject

class CinepolisRepository @Inject constructor(
    private val remoteDataSource: CinepolisRemoteDataSource,
    private val localDataSource: CinepolisDAO
) {

    //Login
    fun getLoginfo(username: String, password: String) = performGetOperation(
        databaseQuery = { localDataSource.getLoginfoLoc() },
        networkCall = { remoteDataSource.getLoginfoRem(username, password) },
        saveCallResult = { localDataSource.insertAllLoginfo(it) }
    )

    //Profile
    fun getProfile(token: String) = performGetOperation(
        databaseQuery = { localDataSource.getProfileLoc() },
        networkCall = { remoteDataSource.getProfileRem(token) },
        saveCallResult = { localDataSource.insertAllProfile(it) }
    )

    //Movies
    fun getMovies() = performGetOperation(
        databaseQuery = { localDataSource.getMoviesLoc() },
        networkCall = { remoteDataSource.getMoviesRem() },
        saveCallResult = { localDataSource.insertAllMovies(it.movies) }
    )

    //Movie
    fun getMovie(id: Int) = localDataSource.getMovieLoc(id)

}