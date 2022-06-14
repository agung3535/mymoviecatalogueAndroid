package com.tuyp.mymovie.view.adapter.pagingAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tuyp.mymovie.R
import com.tuyp.mymovie.data.local.entity.TvShowEntity
import com.tuyp.mymovie.databinding.ItemTvBinding
import com.tuyp.mymovie.utils.ConstVal

class TvPagingAdapter(private val clickHandler: (TvShowEntity) -> Unit): PagedListAdapter<TvShowEntity,TvPagingAdapter.TvViewHolder>(DIFF_CALLBACK) {


    companion object {
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.showId == newItem.showId
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        return TvViewHolder(ItemTvBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val tv = getItem(position)
        if (tv != null ) {
            holder.bind(tv)
            holder.itemView.setOnClickListener {
                clickHandler(tv)
            }
        }
    }

    inner class TvViewHolder(val binding: ItemTvBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TvShowEntity) {
            binding.apply {
                imgMovie.load(ConstVal.IMG_URL + data.backdropPath) {
                    crossfade(true)
                    placeholder(R.drawable.ic_baseline_broken_image_24)
                }
                txtMovieName.text = data.title
                txtLanguage.text = data.originalLanguage
                txtPopularty.text = data.popularity.toString()
            }
        }
    }
}