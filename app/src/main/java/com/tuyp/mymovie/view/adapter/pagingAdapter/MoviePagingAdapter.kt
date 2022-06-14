package com.tuyp.mymovie.view.adapter.pagingAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tuyp.mymovie.data.local.entity.MovieEntity
import com.tuyp.mymovie.databinding.ItemMovieBinding
import com.tuyp.mymovie.utils.ConstVal

class MoviePagingAdapter(private val clickHandler: (MovieEntity) -> Unit): PagedListAdapter<MovieEntity,MoviePagingAdapter.MovieViewHolder>(DIFF_CALLBACK) {


    companion object {
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
            holder.itemView.setOnClickListener {
                clickHandler(movie)
            }
        }


    }

    inner class MovieViewHolder(val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(dataMovie: MovieEntity) {
            binding.apply {
                imgMovie.load(ConstVal.IMG_URL + dataMovie.backdropPath)
                txtMovieName.text = dataMovie.title
                txtLanguage.text = dataMovie.originalLanguage
                txtPopularty.text = dataMovie.popularity.toString()
            }
        }
    }
}