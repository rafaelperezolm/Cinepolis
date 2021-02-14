package com.rafaelperezolm.cinepolis.data.entities


import com.google.gson.annotations.SerializedName

data class Route(
    @SerializedName("code")
    val code: String,
    @SerializedName("sizes")
    val sizes: Sizes
)