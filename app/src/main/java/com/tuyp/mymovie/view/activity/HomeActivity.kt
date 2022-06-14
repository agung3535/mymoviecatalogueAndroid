package com.tuyp.mymovie.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.tuyp.mymovie.R
import com.tuyp.mymovie.databinding.ActivityMainBinding
import com.tuyp.mymovie.view.adapter.TabAdapter
import com.tuyp.mymovie.view.fragment.HomeFragment
import com.tuyp.mymovie.view.fragment.MovieFragment
import com.tuyp.mymovie.view.fragment.ProfileFragment
import com.tuyp.mymovie.view.fragment.TvShowFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        if (savedInstanceState == null) {
            val fragment = HomeFragment.newInstance()
            addFragment(fragment)
        }
        viewBinding.apply {
            bottomNav.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> {
                        val fragment = HomeFragment.newInstance()
                        addFragment(fragment)
                        return@setOnNavigationItemSelectedListener true
                    }
                    R.id.profile -> {
                        val fragment = ProfileFragment.newInstance()
                        addFragment(fragment)
                        return@setOnNavigationItemSelectedListener true
                    }
                }
                false
            }
        }

    }

    override fun onResume() {
        super.onResume()
        val supportActionBar: ActionBar? = (this).supportActionBar
        if (supportActionBar != null) supportActionBar.hide()
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.nav_default_enter_anim, R.anim.nav_default_exit_anim)
            .replace(R.id.base_container, fragment, fragment.javaClass.getSimpleName())
            .commit()
    }


}