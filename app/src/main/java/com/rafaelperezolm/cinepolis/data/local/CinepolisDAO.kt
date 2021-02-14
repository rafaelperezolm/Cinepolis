package com.rafaelperezolm.cinepolis.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rafaelperezolm.cinepolis.data.entities.Loginfo
import com.rafaelperezolm.cinepolis.data.entities.Movy
import com.rafaelperezolm.cinepolis.data.entities.Profile

@Dao
interface CinepolisDAO {

    @Query("SELECT * FROM loginfo")
    fun getLoginfoLoc() : LiveData<List<Loginfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLoginfo(loginfo: Loginfo)

    @Query("SELECT * FROM profile")
    fun getProfileLoc() : LiveData<List<Profile>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProfile(profile: Profile)

    @Query("SELECT * FROM movy")
    fun getMoviesLoc() : LiveData<List<Movy>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(movies: List<Movy>)

    @Query("SELECT * FROM movy WHERE id = :id")
    fun getMovieLoc(id: Int): LiveData<Movy>

}