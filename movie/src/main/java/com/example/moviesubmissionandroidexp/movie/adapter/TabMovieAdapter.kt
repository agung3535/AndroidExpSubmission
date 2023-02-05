package com.example.moviesubmissionandroidexp.movie.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moviesubmissionandroidexp.movie.ui.NowPlayingFragment
import com.example.moviesubmissionandroidexp.movie.ui.UpcomingFragment

class TabMovieAdapter(fm: FragmentManager, lc: Lifecycle): FragmentStateAdapter(fm, lc) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> NowPlayingFragment()
            else -> UpcomingFragment()
        }
    }
}