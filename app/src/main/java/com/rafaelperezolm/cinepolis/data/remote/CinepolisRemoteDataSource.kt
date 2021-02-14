package com.rafaelperezolm.cinepolis.data.remote

import com.rafaelperezolm.cinepolis.common.Const
import com.rafaelperezolm.cinepolis.data.entities.Loginfo
import com.rafaelperezolm.cinepolis.data.entities.Movie
import com.rafaelperezolm.cinepolis.data.entities.Profile
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Response
import javax.annotation.Resource
import javax.inject.Inject


class CinepolisRemoteDataSource @Inject constructor(
    private val cinepolisService: CinepolisService
): BaseDataSource() {

    //Login
    suspend fun getLoginfoRem(username: String, password: String): com.rafaelperezolm.cinepolis.utils.Resource<Loginfo> {
        val body: RequestBody = RequestBody.create(MediaType.parse("text/plain"), "country_code=MX&username=$username&password=$password&grant_type=password&client_id=IATestCandidate&client_secret=c840457e777b4fee9b510fbcd4985b68")
        return getResult { cinepolisService.getLoginfoRemCall(Const.API_KEY, body) }
    }

    //Profile
    suspend fun getProfileRem(token: String): com.rafaelperezolm.cinepolis.utils.Resource<Profile> {
        val auth: String = "bearer $token"
        return getResult { cinepolisService.getProfileRemCall(Const.API_KEY, auth) }
    }

    //Movies
    suspend fun getMoviesRem() = getResult { cinepolisService.getMoviesRemCall(Const.API_KEY) }

}