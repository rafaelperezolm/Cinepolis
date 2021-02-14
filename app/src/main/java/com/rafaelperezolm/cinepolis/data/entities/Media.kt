package com.rafaelperezolm.cinepolis.data.entities


import com.google.gson.annotations.SerializedName

data class Media(
    @SerializedName("code")
    val code: String,
    @SerializedName("resource")
    val resource: String,
    @SerializedName("type")
    val type: String
)