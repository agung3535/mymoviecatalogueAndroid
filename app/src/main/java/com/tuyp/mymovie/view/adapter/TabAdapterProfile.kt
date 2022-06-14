package com.tuyp.mymovie.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabAdapterProfile(fa: FragmentActivity, val listFragment: List<Fragment>): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = listFragment.size

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment?
        fragment = listFragment[position]
        return fragment
    }
}