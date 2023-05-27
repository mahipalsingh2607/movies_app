package com.example.movieapp.network

import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        private const val BASE_URL = "https://www.omdbapi.com/"

        private val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        private val okhttpInterceptor = OkHttpProfilerInterceptor()
        private val client = OkHttpClient.Builder()
            .addInterceptor(okhttpInterceptor)
            .build()

        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val apiService: ApiService by lazy {
            retrofit.create(ApiService::class.java)
        }
    }
}
