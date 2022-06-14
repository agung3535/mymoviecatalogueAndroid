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
import com.tuyp.mymovie.data.local.entity.TvShowEntity
import com.tuyp.mymovie.databinding.ActivityDetailTvBinding
import com.tuyp.mymovie.utils.ConstVal
import com.tuyp.mymovie.view.adapter.GenreAdapter
import com.tuyp.mymovie.view.adapter.GenreTvAdapter
import com.tuyp.mymovie.viewmodel.DetailViewModel
import com.tuyp.mymovie.vo.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailTvActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityDetailTvBinding
    val detailViewModel: DetailViewModel by viewModels()
    lateinit var genreAdapter: GenreTvAdapter
    var detailTv = MutableLiveData<TvShowEntity>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDetailTvBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        genreAdapter = GenreTvAdapter()
        viewBinding.apply {
            if (intent != null) {
                intent.getStringExtra(EXTRA_SHOWID).toString()?.let { extra ->
                    detailViewModel.setShowId(extra)

                    detailViewModel.getDetailShow().observe(this@DetailTvActivity, {dataShow ->
                        if (dataShow != null) {
                            when(dataShow.status) {
                                Status.LOADING -> progressBar.visibility = View.VISIBLE
                                Status.SUCCESS -> {
                                    progressBar.visibility = View.GONE
                                    contentDetail.visibility = View.VISIBLE
                                    recGenre.visibility = View.VISIBLE
                                    dataShow.data?.let { tvData ->
                                        detailTv.value = tvData.tvShow
                                        txtTitle.text = tvData.tvShow.title
                                        txtDescription.text = if (tvData.tvShow.overview != "") tvData.tvShow.overview else "Tidak Ada Deskripsi"
                                        imgMovieDetail.load(ConstVal.IMG_URL + tvData.tvShow.backdropPath)
                                        genreAdapter.setGenre(tvData.tvGenre)
                                        genreAdapter.notifyDataSetChanged()
                                    }
                                }
                                Status.ERROR -> {
                                    progressBar.visibility = View.GONE
                                    Toast.makeText(this@DetailTvActivity,"Terjadi kesalahan",Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    })
                    recGenre.layoutManager = LinearLayoutManager(this@DetailTvActivity,LinearLayoutManager.HORIZONTAL,false)
                    recGenre.adapter = genreAdapter
                    genreAdapter.notifyDataSetChanged()
                }
            }
            btnBack.setOnClickListener {
                finish()
            }

            detailViewModel.cekFavoriteTv().observe(this@DetailTvActivity,{data ->
                if (data != null) {
                    unfavoriteBtn.visibility = View.VISIBLE
                    favoriteBtn.visibility = View.GONE
                }else {
                    favoriteBtn.visibility = View.VISIBLE
                    unfavoriteBtn.visibility = View.GONE
                }
            })

            favoriteBtn.setOnClickListener {
                detailTv.value?.let { data ->
                    CoroutineScope(IO).launch {
                        detailViewModel.addFavShow(data)
                    }
                }

            }

            unfavoriteBtn.setOnClickListener {
                detailViewModel.cekFavoriteTv().observe(this@DetailTvActivity,{data ->
                    if (data != null) {
                        CoroutineScope(IO).launch {
                            detailViewModel.deleteFavShow(data)
                        }
                    }
                })
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val supportActionBar: ActionBar? = (this).supportActionBar
        if (supportActionBar != null) supportActionBar.hide()
    }

    companion object {
        const val EXTRA_SHOWID = "showId"
    }
}