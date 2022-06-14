package com.tuyp.mymovie.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tuyp.mymovie.data.local.entity.GenreMovieEntity
import com.tuyp.mymovie.data.local.entity.GenreTvEntity
import com.tuyp.mymovie.databinding.ListGenreBinding

class GenreTvAdapter: RecyclerView.Adapter<GenreTvAdapter.GenreViewHolder>() {

    var listGenre= ArrayList<GenreTvEntity>()

    fun setGenre(list: List<GenreTvEntity>) {
        if (list == null) return
        this.listGenre.clear()
        this.listGenre.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(ListGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(listGenre[position])
    }

    override fun getItemCount(): Int = listGenre.size

    class GenreViewHolder(val binding: ListGenreBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(dataGenre: GenreTvEntity) {
            binding.apply {
                txtGenre.text = dataGenre.name
            }
        }
    }
}