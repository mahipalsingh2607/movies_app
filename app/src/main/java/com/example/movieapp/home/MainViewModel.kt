package com.example.movieapp.home

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.MoviesResponse
import com.example.movieapp.model.SearchItem
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

class MainViewModel : ViewModel() {

    private val movieList = MutableLiveData<ArrayList<SearchItem?>?>()
    var page = 1
    var progressVisible = ObservableField(false)
    suspend fun getMovies(search: String) {
        return withContext(Dispatchers.IO) {
            progressVisible.set(true)
            val response = ApiClient.apiService.getMovies(API_KEY, search, "movie", page)
            response.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<Response<ResponseBody>> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: Response<ResponseBody>) {
                        progressVisible.set(false)
                        try {
                            val res =
                                Gson().fromJson(t.body()!!.string(), MoviesResponse::class.java)
                            res.search?.let {
                                movieList.postValue(res.search)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                    }

                    override fun onError(e: Throwable) {
                        progressVisible.set(false)
                        Log.i("onError", e.toString())
                        movieList.postValue(null)

                    }

                    override fun onComplete() {
                        progressVisible.set(false)

                    }
                })
        }
    }

    fun getMovieData(): MutableLiveData<ArrayList<SearchItem?>?> {
        return movieList
    }
}