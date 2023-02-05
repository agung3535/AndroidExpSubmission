package com.example.moviesubmissionandroidexp.movie.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesubmissionandroidexp.core.entities.Resource
import com.example.moviesubmissionandroidexp.core.presentation.adapter.upcmingmovie.UpcomingMovieAdapter
import com.example.moviesubmissionandroidexp.di.MovieModules
import com.example.moviesubmissionandroidexp.movie.R
import com.example.moviesubmissionandroidexp.movie.databinding.FragmentUpcomingBinding
import com.example.moviesubmissionandroidexp.movie.di.DaggerMovieComponents
import com.example.moviesubmissionandroidexp.movie.factory.UpcomingVMFactory
import com.example.moviesubmissionandroidexp.movie.viewmodel.UpcomingMovieViewModel
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.launch
import javax.inject.Inject


class UpcomingFragment : Fragment() {

    @Inject
    lateinit var factory: UpcomingVMFactory

    private val upcomingViewModel: UpcomingMovieViewModel by viewModels {
        factory
    }

    lateinit var binding: FragmentUpcomingBinding
    lateinit var upcomingMovieAdapter: UpcomingMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        DaggerMovieComponents.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext().applicationContext, MovieModules::class.java
                )
            ).build()
            .inject(this)
        binding = FragmentUpcomingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        upcomingMovieAdapter = UpcomingMovieAdapter() { data ->
            findNavController().navigate(MovieFragmentDirections.actionMovieFragmentToDetailMovieFragment(data.id.toString()))
        }
        initUI()
        fetchData()
        observeData()
    }

    private fun fetchData() {
        upcomingViewModel.getUpcomingMovie()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                upcomingViewModel.upcomingData.collect { dataState ->
                    when(dataState) {
                        is Resource.Error ->  {
                            showProgressBar(false)
                            Toast.makeText(requireContext(),dataState.message.toString(), Toast.LENGTH_LONG).show()
                        }
                        is Resource.Loading -> showProgressBar(true)
                        is Resource.Success -> {
                            showProgressBar(false)
                            upcomingMovieAdapter.submitList(dataState.data)
                        }
                        null -> {
                            showProgressBar(false)
                            Toast.makeText(requireContext(),"Data null", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
//        lifecycleScope.launch {
//
//        }
    }

    private fun initUI() {
        binding.apply {
            recUpcoming.layoutManager = GridLayoutManager(requireContext(), 2)
            recUpcoming.adapter = upcomingMovieAdapter
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val appBar = requireActivity().actionBar
        appBar?.hide()
    }

    private fun showProgressBar(display : Boolean)
    {
        if(!display)
            binding.progressBar.visibility = View.GONE
        else
            binding.progressBar.visibility = View.VISIBLE
    }


}