package com.rafaelperezolm.cinepolis.data.entities


import com.google.gson.annotations.SerializedName

data class Cast(
    @SerializedName("label")
    val label: String,
    @SerializedName("value")
    val value: List<String>
)