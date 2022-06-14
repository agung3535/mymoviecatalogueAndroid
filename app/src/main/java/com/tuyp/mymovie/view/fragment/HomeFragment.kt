package com.tuyp.mymovie.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import com.google.android.material.tabs.TabLayoutMediator
import com.tuyp.mymovie.R
import com.tuyp.mymovie.databinding.FragmentHomeBinding
import com.tuyp.mymovie.view.activity.HomeActivity
import com.tuyp.mymovie.view.adapter.TabAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    var listFragment =  ArrayList<Fragment>()
    lateinit var movieFragment: MovieFragment
    lateinit var tvShowFragment: TvShowFragment
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieFragment = MovieFragment()
        tvShowFragment = TvShowFragment()
        binding.apply {
            listFragment.add(movieFragment)
            listFragment.add(tvShowFragment)
            val tabAdapter = activity?.let { TabAdapter(it, listFragment) }
            vPagerHome.adapter = tabAdapter
            if (tabAdapter != null) {
                tabAdapter.notifyDataSetChanged()
            }
            TabLayoutMediator(tabLayout,vPagerHome) {tab, position ->
                tab.text = getString(HomeFragment.TAB_TITLES[position])
            }.attach()
        }
    }

    companion object{

        fun newInstance(): HomeFragment{
            val fragment = HomeFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }

        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movie,R.string.tv_show)
    }


}