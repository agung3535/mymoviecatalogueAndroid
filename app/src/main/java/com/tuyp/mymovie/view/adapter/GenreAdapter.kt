package com.tuyp.mymovie.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tuyp.mymovie.data.local.entity.GenreMovieEntity
import com.tuyp.mymovie.data.remote.model.resource.Genre
import com.tuyp.mymovie.databinding.ListGenreBinding

class GenreAdapter: RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    var listGenre= ArrayList<GenreMovieEntity>()

    fun setGenre(list: List<GenreMovieEntity>) {
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
        fun bind(dataGenre: GenreMovieEntity) {
            binding.apply {
                txtGenre.text = dataGenre.name
            }
        }
    }
}