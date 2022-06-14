package com.tuyp.mymovie.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import com.google.android.material.tabs.TabLayoutMediator
import com.tuyp.mymovie.R
import com.tuyp.mymovie.databinding.FragmentProfileBinding
import com.tuyp.mymovie.view.adapter.TabAdapterProfile
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    var arrayFragment = arrayListOf<Fragment>()
    lateinit var tabAdapter: TabAdapterProfile
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val favMovie = FavoriteMovieFragment()
        val favTv = FavoriteTvFragment()
        arrayFragment.add(favMovie)
        arrayFragment.add(favTv)
        binding.apply {
            tabAdapter = activity?.let { TabAdapterProfile(it,arrayFragment) }!!
            vPagerProfile.adapter = tabAdapter
            tabAdapter.notifyDataSetChanged()
            TabLayoutMediator(tabLayoutProfile,vPagerProfile) {tab,position ->
                tab.text = getString(TAB_TITLES_PROFILE[position])
            }.attach()
        }
    }


    companion object {
        fun newInstance(): ProfileFragment {
            val fragment = ProfileFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }

        @StringRes
        val TAB_TITLES_PROFILE = intArrayOf(R.string.my_movie,R.string.my_show)
    }
}