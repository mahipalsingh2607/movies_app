package com.example.movieapp.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesResponse(

    @Expose
    @SerializedName("Response")
    val response: String? = null,

    @Expose
    @SerializedName("totalResults")
    val totalResults: String? = null,

    @Expose
    @SerializedName("Search")
    val search: ArrayList<SearchItem?>? = null
) : Parcelable

@Parcelize
data class SearchItem(

    @Expose
    @SerializedName("Type")
    val type: String? = null,

    @Expose
    @SerializedName("Year")
    val year: String? = null,

    @Expose
    @SerializedName("imdbID")
    val imdbID: String? = null,

    @Expose
    @SerializedName("Poster")
    val poster: String? = null,

    @Expose
    @SerializedName("Title")
    val title: String? = null
) : Parcelable
