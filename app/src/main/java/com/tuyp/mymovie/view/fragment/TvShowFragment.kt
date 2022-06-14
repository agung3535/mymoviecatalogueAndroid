package com.tuyp.mymovie.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.tuyp.mymovie.R
import com.tuyp.mymovie.databinding.FragmentTvShowBinding
import com.tuyp.mymovie.view.activity.DetailTvActivity
import com.tuyp.mymovie.view.adapter.TvAdapter
import com.tuyp.mymovie.view.adapter.pagingAdapter.TvPagingAdapter
import com.tuyp.mymovie.viewmodel.HomeViewModel
import com.tuyp.mymovie.vo.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment : Fragment() {

    lateinit var viewBinding: FragmentTvShowBinding
    private val homeViewModel: HomeViewModel by viewModels()
//    lateinit var adapter: TvAdapter
    lateinit var adapterPaging: TvPagingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentTvShowBinding.inflate(layoutInflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        adapter = TvAdapter() { data ->
//            val intent = Intent(requireActivity(), DetailTvActivity::class.java)
//            intent.putExtra("showId", data.showId.toString())
//            startActivity(intent)
//        }
        adapterPaging = TvPagingAdapter { data ->
            val intent = Intent(requireActivity(), DetailTvActivity::class.java)
            intent.putExtra("showId", data.showId.toString())
            startActivity(intent)
        }

        viewBinding.apply {
//            homeViewModel.listTvShow().observe(viewLifecycleOwner,{ dataShow ->
//                adapter.setTv(dataShow)
//                adapter.notifyDataSetChanged()
//                progressBar.visibility = View.GONE
//                recShow.visibility = View.VISIBLE
//                errorLayut.visibility = View.GONE
//            })
            recShow.layoutManager = GridLayoutManager(requireActivity(),2)
            recShow.adapter = adapterPaging
            homeViewModel.listTvShow().observe(viewLifecycleOwner, {dataShow ->
                if (dataShow != null) {
                    when (dataShow.status) {
                        Status.LOADING -> progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            progressBar.visibility = View.GONE
                            recShow.visibility = View.VISIBLE
                            dataShow.data?.let {
//                                adapter.setTv(it)
                                adapterPaging.submitList(it)
//                                adapter.notifyDataSetChanged()
                            }
                        }
                        Status.ERROR -> {
                            progressBar.visibility = View.GONE
                            Toast.makeText(activity,"Terjadi kesalahan", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })

//            homeViewModel.checkErrorTv().observe(viewLifecycleOwner,{ message ->
//                if (message != null){
//                    txtError.text = message
//                    errorLayut.visibility = View.VISIBLE
//                    progressBar.visibility = View.GONE
//                    recShow.visibility = View.GONE
//                }
//            })

        }

    }


}