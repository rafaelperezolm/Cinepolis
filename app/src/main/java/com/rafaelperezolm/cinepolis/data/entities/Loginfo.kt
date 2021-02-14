package com.rafaelperezolm.cinepolis.data.entities


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "loginfo")
data class Loginfo(
    @SerializedName("access_token")
    val accessToken: String,
    @PrimaryKey
    @SerializedName("as:client_id")
    val asClientId: String,
    @SerializedName("country_code")
    val countryCode: String,
    @SerializedName(".expires")
    val expires: String,
    @SerializedName("expires_in")
    val expiresIn: Int,
    @SerializedName(".issued")
    val issued: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("username")
    val username: String
)