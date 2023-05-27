package com.example.movieapp.moviedetails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMovieDetailsBinding
import com.example.movieapp.model.SearchItem

class MovieDetailsActivity : ComponentActivity() {
    lateinit var binding: ActivityMovieDetailsBinding
    var movieData : SearchItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        getIntentData()
        initViews()
    }

    private fun initViews() {
        movieData?.apply {
            Glide.with(this@MovieDetailsActivity)
                .load(poster)
                .into(binding.ivMovie)
            binding.tvMovieName.text = title
            binding.tvYear.text = year
        }
    }

    private fun getIntentData() {
        movieData = intent.getParcelableExtra("movieData")
    }
}