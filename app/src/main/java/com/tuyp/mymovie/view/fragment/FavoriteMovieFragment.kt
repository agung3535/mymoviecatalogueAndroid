package com.tuyp.mymovie.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuyp.mymovie.R
import com.tuyp.mymovie.databinding.FragmentFavoriteMovieBinding
import com.tuyp.mymovie.view.adapter.FavMovieAdapter
import com.tuyp.mymovie.view.adapter.pagingAdapter.FavMoviePagingAdapter
import com.tuyp.mymovie.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteMovieFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by viewModels()
    lateinit var viewBinding: FragmentFavoriteMovieBinding
    lateinit var favAdapter: FavMovieAdapter
    lateinit var favPagingAdapter: FavMoviePagingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentFavoriteMovieBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.apply {
            recFavMovie.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
            profileViewModel.getFavoriteMovie().observe(viewLifecycleOwner, {dataFavorite ->
                if (dataFavorite.size != 0){
//                    favAdapter = FavMovieAdapter()
                    favPagingAdapter = FavMoviePagingAdapter()
                    favPagingAdapter.submitList(dataFavorite)
//                    favAdapter.setFavMove(dataFavorite)
                    recFavMovie.adapter = favPagingAdapter
                    favPagingAdapter.notifyDataSetChanged()
//                    favAdapter.notifyDataSetChanged()
                    errorLayut.visibility = View.GONE
                    recFavMovie.visibility = View.VISIBLE
                }else{
                    Log.d("dataaa","masuk error layout")
                    recFavMovie.visibility = View.GONE
                    errorLayut.visibility = View.VISIBLE
                }
            })
        }
    }



   companion object {
       fun newInstance(): FavoriteMovieFragment {
           val fragment = FavoriteMovieFragment()
           val arg = Bundle()
           fragment.arguments = arg
           return fragment
       }
   }
}