package com.tuyp.mymovie.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tuyp.mymovie.data.local.entity.MyFavoriteMovie
import com.tuyp.mymovie.databinding.ItemFavoriteBinding
import com.tuyp.mymovie.utils.ConstVal

class FavMovieAdapter: RecyclerView.Adapter<FavMovieAdapter.FavMovieViewHolder>() {

    var favMovieList = ArrayList<MyFavoriteMovie>()

    fun setFavMove(favMovie: List<MyFavoriteMovie>) {
        if (favMovie == null) return
        this.favMovieList.clear()
        this.favMovieList.addAll(favMovie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMovieViewHolder {
        return FavMovieViewHolder(ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun onBindViewHolder(holder: FavMovieViewHolder, position: Int) {
        holder.bind(favMovieList[position])
    }

    override fun getItemCount(): Int = favMovieList.size

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