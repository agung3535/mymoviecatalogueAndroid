package com.tuyp.mymovie.view.adapter.pagingAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tuyp.mymovie.data.local.entity.MyFavoriteMovie
import com.tuyp.mymovie.databinding.ItemFavoriteBinding
import com.tuyp.mymovie.utils.ConstVal

class FavMoviePagingAdapter: PagedListAdapter<MyFavoriteMovie,FavMoviePagingAdapter.FavMovieViewHolder>(
    DIFF_CALLBACK) {


    companion object {
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<MyFavoriteMovie>() {
            override fun areItemsTheSame(
                oldItem: MyFavoriteMovie,
                newItem: MyFavoriteMovie
            ): Boolean {
                return oldItem.favMovieId == newItem.favMovieId
            }

            override fun areContentsTheSame(
                oldItem: MyFavoriteMovie,
                newItem: MyFavoriteMovie
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMovieViewHolder {
        return FavMovieViewHolder(ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: FavMovieViewHolder, position: Int) {
        val favMovie = getItem(position)
        if (favMovie != null) {
            holder.bind(favMovie)
        }
    }

    inner class FavMovieViewHolder(val binding: ItemFavoriteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(favorite: MyFavoriteMovie) {
            binding.apply {
                title.text = favorite.title
                imgView.load(ConstVal.IMG_URL + favorite.backdropPath)
                txtPopularty.text = favorite.popularity
            }
        }
    }
}