package com.example.movieapp.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Parcelize
data class MovieDetailsResponse(

    @Expose
    @SerializedName("Metascore")
    val metascore: String? = null,

    @Expose
    @SerializedName("BoxOffice")
    val boxOffice: String? = null,

    @Expose
    @SerializedName("Website")
    val website: String? = null,

    @Expose
    @SerializedName("imdbRating")
    val imdbRating: String? = null,

    @Expose
    @SerializedName("imdbVotes")
    val imdbVotes: String? = null,

    @Expose
    @SerializedName("Ratings")
    val ratings: List<RatingsItem?>? = null,

    @Expose
    @SerializedName("Runtime")
    val runtime: String? = null,

    @Expose
    @SerializedName("Language")
    val language: String? = null,

    @Expose
    @SerializedName("Rated")
    val rated: String? = null,

    @Expose
    @SerializedName("Production")
    val production: String? = null,

    @Expose
    @SerializedName("Released")
    val released: String? = null,

    @Expose
    @SerializedName("imdbID")
    val imdbID: String? = null,

    @Expose
    @SerializedName("Plot")
    val plot: String? = null,

    @Expose
    @SerializedName("Director")
    val director: String? = null,

    @Expose
    @SerializedName("Title")
    val title: String? = null,

    @Expose
    @SerializedName("Actors")
    val actors: String? = null,

    @Expose
    @SerializedName("Response")
    val response: String? = null,

    @Expose
    @SerializedName("Type")
    val type: String? = null,

    @Expose
    @SerializedName("Awards")
    val awards: String? = null,

    @Expose
    @SerializedName("DVD")
    val dVD: String? = null,

    @Expose
    @SerializedName("Year")
    val year: String? = null,

    @Expose
    @SerializedName("Poster")
    val poster: String? = null,

    @Expose
    @SerializedName("Country")
    val country: String? = null,

    @Expose
    @SerializedName("Genre")
    val genre: String? = null,

    @Expose
    @SerializedName("Writer")
    val writer: String? = null
) : Parcelable

@Parcelize
data class RatingsItem(

    @Expose
    @SerializedName("Value")
    val value: String? = null,

    @Expose
    @SerializedName("Source")
    val source: String? = null
) : Parcelable
