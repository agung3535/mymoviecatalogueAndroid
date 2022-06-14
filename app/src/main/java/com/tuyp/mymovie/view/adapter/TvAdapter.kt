package com.tuyp.mymovie.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tuyp.mymovie.R
import com.tuyp.mymovie.data.local.entity.TvShowEntity
import com.tuyp.mymovie.data.remote.model.resource.TvShowResource
import com.tuyp.mymovie.databinding.ItemTvBinding
import com.tuyp.mymovie.utils.ConstVal

class TvAdapter(private val clickHandler: (TvShowEntity) -> Unit): RecyclerView.Adapter<TvAdapter.TvViewHolder>() {

    private var listTv = ArrayList<TvShowEntity>()

    fun setTv(tvShow: List<TvShowEntity>) {
        if (tvShow == null) return
        listTv.clear()
        listTv.addAll(tvShow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        return TvViewHolder(ItemTvBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        holder.bind(listTv[position])
        holder.itemView.setOnClickListener {
            clickHandler(listTv[position])
        }
    }

    override fun getItemCount(): Int = listTv.size

    class TvViewHolder(val binding: ItemTvBinding): RecyclerView.ViewHolder(binding.root) {
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