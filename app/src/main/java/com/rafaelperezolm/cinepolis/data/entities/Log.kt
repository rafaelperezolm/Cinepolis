package com.rafaelperezolm.cinepolis.data.entities

import com.google.gson.annotations.SerializedName

data class Log(
    @SerializedName("log")
    val loginfo: List<Loginfo>
)