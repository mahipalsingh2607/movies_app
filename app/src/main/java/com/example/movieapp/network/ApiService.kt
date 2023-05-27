package com.example.movieapp.network


import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("/")
    fun getMovies(
        @Query("apikey") apikey: String,
        @Query("s") s: String,
        @Query("type") type: String,
        @Query("page") page: Int
    ): Observable<Response<ResponseBody>>

}