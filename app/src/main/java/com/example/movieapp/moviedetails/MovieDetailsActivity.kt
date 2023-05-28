package com.example.movieapp.moviedetails

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMovieDetailsBinding
import com.example.movieapp.home.MainViewModel
import com.example.movieapp.model.SearchItem

class MovieDetailsActivity : ComponentActivity() {
    var viewmodel: MovieDetailsViewModel? = null
    lateinit var binding: ActivityMovieDetailsBinding
    var movieData : SearchItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        binding.viewModel = viewmodel
        getIntentData()
        subscribeToRepoData()
        initViews()
    }

    private fun subscribeToRepoData() {
        viewmodel?.getMovieData()?.observe(this, Observer {
            it?.let {
                binding.tvMovieName.text = "Title : ${it.title}"
                binding.tvYear.text = "Year : ${it.year}"
                binding.tvReleased.text = "Release Date : ${it.released}"
                binding.tvRuntime.text = "Runtime : ${it.runtime}"
                binding.tvGenre.text = "Genre : ${it.genre}"
                binding.tvDirector.text = "Director : ${it.director}"
                binding.tvWriter.text = "Writer : ${it.writer}"
                binding.tvActors.text = "Actors : ${it.actors}"
                binding.tvPlot.text = "Plot : ${it.plot}"
                binding.tvLanguage.text = "Language : ${it.language}"
                binding.tvRating.text = "IMDB Rating : ${it.imdbRating}"
            }
        })
    }

    private fun initViews() {
        movieData?.apply {
            if(poster?.endsWith(".jpg") == true) {
                Glide.with(this@MovieDetailsActivity)
                    .load(poster)
                    .into(binding.ivMovie)
            }
            kotlinx.coroutines.runBlocking {
                viewmodel?.getMovieDetails(imdbID ?: "")
            }

        }
    }

    private fun getIntentData() {
        movieData = intent.getParcelableExtra("movieData")
    }
}