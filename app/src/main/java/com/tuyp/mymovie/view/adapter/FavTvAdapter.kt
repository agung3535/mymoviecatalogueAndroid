package com.tuyp.mymovie.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tuyp.mymovie.data.local.entity.MyFavoriteTvShow
import com.tuyp.mymovie.databinding.ItemFavoriteBinding
import com.tuyp.mymovie.utils.ConstVal

class FavTvAdapter: RecyclerView.Adapter<FavTvAdapter.FavTvViewHolder>() {

    var favTvList = ArrayList<MyFavoriteTvShow>()

    fun setFavTv(favTv: List<MyFavoriteTvShow>) {
        if (favTv == null) return
        favTvList.clear()
        favTvList.addAll(favTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavTvViewHolder {
        return FavTvViewHolder(ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: FavTvViewHolder, position: Int) {
        holder.bind(favTvList[position])
    }

    override fun getItemCount(): Int = favTvList.size

    inner class FavTvViewHolder(val binding: ItemFavoriteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(favTv: MyFavoriteTvShow) {
            binding.apply {
                title.text = favTv.title
                imgView.load(ConstVal.IMG_URL + favTv.backdropPath)

            }
        }
    }
}