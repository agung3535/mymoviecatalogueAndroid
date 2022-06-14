package com.tuyp.mymovie.view.adapter.pagingAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tuyp.mymovie.data.local.entity.MyFavoriteMovie
import com.tuyp.mymovie.data.local.entity.MyFavoriteTvShow
import com.tuyp.mymovie.databinding.ItemFavoriteBinding
import com.tuyp.mymovie.utils.ConstVal

class FavTvPagingAdapter: PagedListAdapter<MyFavoriteTvShow,FavTvPagingAdapter.FavTvViewHolder>(
    DIFF_CALLBACK) {


    companion object {
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<MyFavoriteTvShow>() {
            override fun areItemsTheSame(
                oldItem: MyFavoriteTvShow,
                newItem: MyFavoriteTvShow
            ): Boolean {
                return oldItem.favTvShowId == newItem.favTvShowId
            }

            override fun areContentsTheSame(
                oldItem: MyFavoriteTvShow,
                newItem: MyFavoriteTvShow
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavTvViewHolder {
        return FavTvViewHolder(ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: FavTvViewHolder, position: Int) {
        val favTv = getItem(position)
        if (favTv != null) {
            holder.bind(favTv)
        }

    }

    inner class FavTvViewHolder(val binding: ItemFavoriteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(favorite: MyFavoriteTvShow) {
            binding.apply {
                title.text = favorite.title
                imgView.load(ConstVal.IMG_URL + favorite.backdropPath)
                txtPopularty.text = favorite.popularity
            }
        }
    }
}