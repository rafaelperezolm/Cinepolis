package com.rafaelperezolm.cinepolis.data.entities


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "profile")
data class Profile(
    @PrimaryKey
    @SerializedName("card_number")
    val cardNumber: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("profile_picture")
    val profilePicture: String
)