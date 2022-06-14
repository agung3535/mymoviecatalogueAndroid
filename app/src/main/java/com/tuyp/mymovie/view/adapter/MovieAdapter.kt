package com.tuyp.mymovie.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.tuyp.mymovie.R
import com.tuyp.mymovie.data.local.entity.MovieEntity
import com.tuyp.mymovie.data.remote.model.resource.MovieResource
import com.tuyp.mymovie.databinding.ItemMovieBinding
import com.tuyp.mymovie.utils.ConstVal

class MovieAdapter(private val clickHandler: (MovieEntity) -> Unit): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listMovie = ArrayList<MovieEntity>()
    fun setMyMovie(list: List<MovieEntity>){
        if (list == null) return
        this.listMovie.clear()
        this.listMovie.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])
        holder.itemView.setOnClickListener {
            clickHandler(listMovie[position])
        }
    }

    override fun getItemCount(): Int = listMovie.size

    class MovieViewHolder(val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {
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