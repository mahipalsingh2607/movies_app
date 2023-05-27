package com.example.movieapp.home


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemMovieBinding
import com.example.movieapp.model.SearchItem;

class MoviesAdapter(
    private val mRepoList : ArrayList<SearchItem?>?,
    private val mListener : onClick
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private val moviesDataList = MutableLiveData<ArrayList<SearchItem>>()

    private var mContext: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val binding = DataBindingUtil.inflate<ItemMovieBinding>(
            LayoutInflater.from(parent.context), R.layout.item_movie, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mBinding.tvMovieName.text = mRepoList?.get(position)?.title
        if(mRepoList?.get(position)?.poster?.endsWith("jpg",true) == true) {
            Glide.with(holder.mBinding.ivMovie.context)
                .load(mRepoList?.get(position)?.poster)
                .into(holder.mBinding.ivMovie)
        }
        holder.mBinding.cardView.setOnClickListener {
            mRepoList?.get(position)?.let {
                mListener.onMovieClick(it)
            }
        }

    }

    override fun getItemCount(): Int {
        return mRepoList?.size ?: 0
    }

    class ViewHolder(val mBinding: ItemMovieBinding) : RecyclerView.ViewHolder(
        mBinding.root
    )

    interface onClick{
        fun onMovieClick(repo : SearchItem)
    }

}
