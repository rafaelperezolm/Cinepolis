package com.rafaelperezolm.cinepolis.data.entities


import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movy")
data class Movy(
    //@SerializedName("cast")
    //val cast: List<Cast>,
    //@SerializedName("categories")
    //val categories: List<String>,
    //@SerializedName("cinemas")
    //val cinemas: List<Any>,
    //@SerializedName("code")
    //val code: String,
    @SerializedName("distributor")
    val distributor: String,
    @SerializedName("genre")
    val genre: String,
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("length")
    val length: String,
    @SerializedName("media")
    val media: List<Media>,
    @SerializedName("name")
    val name: String,
    @SerializedName("original_name")
    val originalName: String,
    @SerializedName("position")
    val position: Int,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("synopsis")
    val synopsis: String
)