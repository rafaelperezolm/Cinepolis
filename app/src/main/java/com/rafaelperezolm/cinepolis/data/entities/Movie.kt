package com.rafaelperezolm.cinepolis.data.entities


import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("movies")
    val movies: List<Movy>,
    @SerializedName("routes")
    val routes: List<Route>
)