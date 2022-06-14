package com.tuyp.mymovie.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.tuyp.mymovie.R
import com.tuyp.mymovie.data.local.entity.MovieEntity
import com.tuyp.mymovie.data.remote.model.resource.MovieResource
import com.tuyp.mymovie.databinding.ActivityDetailMovieBinding
import com.tuyp.mymovie.utils.ConstVal
import com.tuyp.mymovie.view.adapter.GenreAdapter
import com.tuyp.mymovie.viewmodel.DetailViewModel
import com.tuyp.mymovie.vo.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityDetailMovieBinding
    lateinit var genreAdapter: GenreAdapter
    var detailMovie = MutableLiveData<MovieEntity>()
    val detailViewModel: DetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        genreAdapter = GenreAdapter()

        viewBinding.apply {
            if (intent.getStringExtra(EXTRA_MOVIEID).toString() != null) {
                var movieID = intent.getStringExtra(EXTRA_MOVIEID).toString()
                detailViewModel.setMovieId(movieID)

                recGenre.layoutManager = LinearLayoutManager(this@DetailMovieActivity,LinearLayoutManager.HORIZONTAL,false)
                recGenre.adapter = genreAdapter
                detailViewModel.cekFavoriteMovie().observe(this@DetailMovieActivity,{data ->
                    Log.d("dataaaa","favorit = $data")
                    if (data != null) {
                        unfavoriteBtn.visibility = View.VISIBLE
                        favoriteBtn.visibility = View.GONE
                    }else {
                        favoriteBtn.visibility = View.VISIBLE
                        unfavoriteBtn.visibility = View.GONE

                    }
                })

                detailViewModel.getDetailMovie().observe(this@DetailMovieActivity, {data ->
                    Log.d("dataaaaa","data dalam detailviewmodel $data")
                    if (data != null) {
                        when(data.status) {
                            Status.LOADING -> progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                progressBar.visibility = View.GONE
                                data.data?.let { dataMovie ->
                                    detailMovie.value = dataMovie.movie
                                    txtTitle.text = dataMovie.movie.title
                                    contentDetail.visibility = View.VISIBLE
                                    recGenre.visibility = View.VISIBLE
                                    imgMovieDetail.load(ConstVal.IMG_URL + dataMovie.movie.backdropPath)
                                    txtDescription.text =
                                        if (dataMovie.movie.overview != "") dataMovie.movie.overview else "Tidak Ada Deskripsi"
                                    genreAdapter.setGenre(dataMovie.genre)
                                    genreAdapter.notifyDataSetChanged()
                                }
                            }
                            Status.ERROR -> {
                                progressBar.visibility = View.GONE
                                Toast.makeText(this@DetailMovieActivity,"Terjadi kesalahan",Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                })

                favoriteBtn.setOnClickListener {
                    CoroutineScope(IO).launch {
                        detailMovie.value?.let { data ->
                            detailViewModel.addFavMovie(data)
                        }
                    }
                }

                unfavoriteBtn.setOnClickListener {

                        detailViewModel.cekFavoriteMovie().observe(this@DetailMovieActivity,{data ->
                            if (data != null) {
                                CoroutineScope(IO).launch {
                                    detailViewModel.deleteFavMovie(data)
                                }

                            }
                        })

                }



            }

            genreAdapter.notifyDataSetChanged()

            btnBack.setOnClickListener {
                finish()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val supportActionBar: ActionBar? = (this).supportActionBar
        if (supportActionBar != null) supportActionBar.hide()
    }

    companion object {
        const val EXTRA_MOVIEID = "movieId"
    }
}