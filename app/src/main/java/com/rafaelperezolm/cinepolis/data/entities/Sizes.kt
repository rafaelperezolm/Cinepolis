package com.rafaelperezolm.cinepolis.data.entities


import com.google.gson.annotations.SerializedName

data class Sizes(
    @SerializedName("large")
    val large: String,
    @SerializedName("medium")
    val medium: String,
    @SerializedName("small")
    val small: String,
    @SerializedName("x-large")
    val xLarge: String
)