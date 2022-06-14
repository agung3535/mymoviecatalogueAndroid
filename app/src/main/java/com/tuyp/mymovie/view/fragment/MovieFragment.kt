package com.tuyp.mymovie.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.tuyp.mymovie.R
import com.tuyp.mymovie.databinding.FragmentMovieBinding
import com.tuyp.mymovie.view.activity.DetailMovieActivity
import com.tuyp.mymovie.view.adapter.MovieAdapter
import com.tuyp.mymovie.view.adapter.pagingAdapter.MoviePagingAdapter
import com.tuyp.mymovie.viewmodel.HomeViewModel
import com.tuyp.mymovie.vo.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    lateinit var viewBinding: FragmentMovieBinding
//    lateinit var movieAdapter: MovieAdapter
//    lateinit var moviePagingAdapter: MoviePagingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentMovieBinding.inflate(layoutInflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val movieAdapter = MovieAdapter() { data ->
//            val intent = Intent(requireActivity(), DetailMovieActivity::class.java)
//            intent.putExtra(DetailMovieActivity.EXTRA_MOVIEID, data.movieId.toString())
//            startActivity(intent)
//        }
        val moviePagingAdapter = MoviePagingAdapter() { data ->
            val intent = Intent(requireActivity(), DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_MOVIEID, data.movieId.toString())
            startActivity(intent)
        }


        viewBinding.apply {
//            homeViewModel.listMovieData().observe(viewLifecycleOwner,{ dataMovie ->
//                movieAdapter.setMyMovie(dataMovie)
//                movieAdapter.notifyDataSetChanged()
//                progressBar.visibility = View.GONE
//                recMovie.visibility = View.VISIBLE
//                errorLayut.visibility = View.GONE
//            })
            recMovie.layoutManager = GridLayoutManager(requireActivity(),2)
            recMovie.adapter = moviePagingAdapter
            homeViewModel.listMovieData().observe(viewLifecycleOwner, {dataMovie ->
                if (dataMovie != null){
                    when(dataMovie.status) {
                        Status.LOADING -> progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {

                            progressBar.visibility = View.GONE
                            recMovie.visibility = View.VISIBLE
                            dataMovie.data?.let {
                                moviePagingAdapter.submitList(it)

                                moviePagingAdapter.notifyDataSetChanged()
                            }

                        }
                        Status.ERROR -> {
                            progressBar.visibility = View.GONE
                            Toast.makeText(activity,"Terjadi kesalahan",Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })
//            homeViewModel.checkError().observe(viewLifecycleOwner,{ message ->
//                if (message != null) {
//                    txtError.text = message
//                    errorLayut.visibility = View.VISIBLE
//                    progressBar.visibility = View.GONE
//                    recMovie.visibility = View.GONE
//                }
//            })

        }
    }

}