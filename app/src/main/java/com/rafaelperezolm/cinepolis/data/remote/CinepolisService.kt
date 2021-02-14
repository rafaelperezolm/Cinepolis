package com.rafaelperezolm.cinepolis.data.remote

import com.rafaelperezolm.cinepolis.data.entities.Loginfo
import com.rafaelperezolm.cinepolis.data.entities.Movie
import com.rafaelperezolm.cinepolis.data.entities.Profile
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface CinepolisService {

    //Login
    @POST("v2/oauth/token")
    suspend fun getLoginfoRemCall(@Header("api_key") apiKey: String, @Body body: RequestBody ) : Response<Loginfo>

    //Profile
    @GET("v1/members/profile?country_code=MX")
    suspend fun getProfileRemCall(@Header("api_key") apiKey: String, @Header("Authorization") token: String) : Response<Profile>

    //Movies
    @GET("v2/movies?country_code=MX&cinemas=61")
    suspend fun getMoviesRemCall(@Header("api_key") apiKey: String) : Response<Movie>

}