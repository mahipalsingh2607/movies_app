package com.example.movieapp.home

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.model.SearchItem
import com.example.movieapp.moviedetails.MovieDetailsActivity
import androidx.lifecycle.Observer
import android.text.TextWatcher
import android.text.Editable
import androidx.recyclerview.widget.GridLayoutManager
import android.widget.Toast

import android.R.string.no




class MainActivity : ComponentActivity(),MoviesAdapter.onClick {
    var viewmodel: MainViewModel? = null
    var gitRepoAdapter : MoviesAdapter? = null
    val mMoviesList = ArrayList<SearchItem?>()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewmodel
        viewmodel?.getMovies("Marvel")
        subscribeToRepoData()
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                viewmodel?.let{ viewmodel ->
                    viewmodel.page = 1
                    viewmodel.getMovies(s.toString())
                }
            }
        }
        binding.etSearchMovie.addTextChangedListener(textWatcher)
        binding.rvMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!binding.rvMovies.canScrollVertically(1)) {
                    viewmodel?.let {
                        it.page++
                        it.getMovies(binding.etSearchMovie.text.toString())
                    }
                }
            }
        })
    }

    fun subscribeToRepoData() {
        gitRepoAdapter =
            MoviesAdapter(mMoviesList, this@MainActivity)
        binding.rvMovies.adapter = gitRepoAdapter
        binding.rvMovies.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                outRect.top = 10
            }

            override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {

            }
        })
        viewmodel?.getMovieData()?.observe(this, Observer {
            it?.let { list ->
                viewmodel?.let { viewmodel ->
                    if (viewmodel.page == 1) {
                        //if page is one than first search
                        mMoviesList.clear()
                        mMoviesList.addAll(list)
                        gitRepoAdapter?.let {
                            it.notifyDataSetChanged()
                        }
                    } else {
                        mMoviesList.addAll(list)
                        gitRepoAdapter?.let {
                            it.notifyItemRangeInserted(viewmodel.page*10,10)
                        }
                    }
                }
            }
        })
    }

    override fun onMovieClick(repo: SearchItem) {
        val movieDetailsIntent = Intent(this,MovieDetailsActivity::class.java)
        movieDetailsIntent.putExtra("movieData",repo)
        startActivity(movieDetailsIntent)

    }




}