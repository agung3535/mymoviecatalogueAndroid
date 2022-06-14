package com.tuyp.mymovie.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuyp.mymovie.R
import com.tuyp.mymovie.databinding.FragmentFavoriteTvBinding
import com.tuyp.mymovie.view.adapter.FavTvAdapter
import com.tuyp.mymovie.view.adapter.pagingAdapter.FavTvPagingAdapter
import com.tuyp.mymovie.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteTvFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by viewModels()
    lateinit var favAdapter: FavTvAdapter
    lateinit var favPagingTvAdapter: FavTvPagingAdapter
    lateinit var viewBinding: FragmentFavoriteTvBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentFavoriteTvBinding.inflate(layoutInflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.apply {
            recFavTv.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
//            favAdapter = FavTvAdapter()
            favPagingTvAdapter = FavTvPagingAdapter()
            profileViewModel.getFavoriteShow().observe(viewLifecycleOwner,{dataFavorite ->
                if (dataFavorite.size != 0){
//                    favAdapter.setFavTv(dataFavorite)
                    favPagingTvAdapter.submitList(dataFavorite)
                    recFavTv.adapter = favPagingTvAdapter
                    favPagingTvAdapter.notifyDataSetChanged()
//                    favAdapter.notifyDataSetChanged()
                    errorLayut.visibility = View.GONE
                    recFavTv.visibility = View.VISIBLE
                }else {
                    errorLayut.visibility = View.VISIBLE
                    recFavTv.visibility = View.GONE
                }
            })
        }
    }




    companion object {
        fun newInstance(): FavoriteTvFragment {
            val fragment = FavoriteTvFragment()
            val arg = Bundle()
            fragment.arguments = arg
            return fragment
        }
    }
}