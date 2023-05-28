package com.example.movieapp.moviedetails

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.MovieDetailsResponse
import com.example.movieapp.network.ApiClient
import com.example.movieapp.util.Constants.API_KEY
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response

class MovieDetailsViewModel : ViewModel() {

    private val movieDetails = MutableLiveData<MovieDetailsResponse?>()
    var progressVisible = ObservableField(false)
    suspend fun getMovieDetails(id: String) {
        return withContext(Dispatchers.IO) {
            progressVisible.set(true)
            val response = ApiClient.apiService.getMovieDetails(API_KEY, id)
            response.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<Response<ResponseBody>> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: Response<ResponseBody>) {
                        progressVisible.set(false)
                        try {
                            val res = Gson().fromJson(
                                t.body()!!.string(),
                                MovieDetailsResponse::class.java
                            )
                            res?.let {
                                movieDetails.postValue(it)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                    }

                    override fun onError(e: Throwable) {
                        progressVisible.set(false)
                        Log.i("onError", e.toString())
                        movieDetails.postValue(null)

                    }

                    override fun onComplete() {
                        progressVisible.set(false)
                    }
                })
        }
    }

    fun getMovieData(): MutableLiveData<MovieDetailsResponse?> {
        return movieDetails
    }
}