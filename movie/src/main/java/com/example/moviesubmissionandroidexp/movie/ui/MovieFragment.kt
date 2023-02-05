package com.example.moviesubmissionandroidexp.movie.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesubmissionandroidexp.movie.R
import com.example.moviesubmissionandroidexp.movie.adapter.TabMovieAdapter
import com.example.moviesubmissionandroidexp.movie.databinding.FragmentMovieBinding
import com.google.android.material.tabs.TabLayoutMediator


class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    val titleTab = arrayListOf("Playing Now","Upcoming")



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val supportActionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
        if (supportActionBar != null) supportActionBar.hide()

    }

    override fun onDetach() {
        super.onDetach()
        val supportActionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
        if (supportActionBar != null) supportActionBar.show()
    }

    override fun onDestroyView() {
//        val vp = binding.viewPagerHome
//        vp.let {
//            it.adapter = null
//        }
        super.onDestroyView()
        binding.viewPagerHome.adapter = null
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewPagerHome.adapter = TabMovieAdapter(this@MovieFragment.childFragmentManager, this@MovieFragment.viewLifecycleOwner.lifecycle)
            TabLayoutMediator(tabLayoutHome, viewPagerHome) { tab, position ->
                tab.text = titleTab[position]
            }.attach()

            customToolbar.setOnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.search -> {
                        Toast.makeText(requireContext(),"KLik", Toast.LENGTH_LONG).show()
                    }
                }
                true
            }

        }



    }


}