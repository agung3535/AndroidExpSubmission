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
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesubmissionandroidexp.core.presentation.adapter.playingnow.PlayingNowAdapter
import com.example.moviesubmissionandroidexp.di.MovieModules
import com.example.moviesubmissionandroidexp.movie.R
import com.example.moviesubmissionandroidexp.movie.databinding.FragmentNowPlayingBinding
import com.example.moviesubmissionandroidexp.movie.di.DaggerMovieComponents
import com.example.moviesubmissionandroidexp.movie.factory.NowPlayingViewModelFactory
import com.example.moviesubmissionandroidexp.movie.viewmodel.NowPlayingViewmodel
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class NowPlayingFragment : Fragment() {

    private var _binding: FragmentNowPlayingBinding? = null
    private val binding: FragmentNowPlayingBinding get() = _binding!!

    @Inject
    lateinit var factory: NowPlayingViewModelFactory

    private val nowPlayingViewmodel: NowPlayingViewmodel by viewModels {
        factory
    }
    lateinit var playingAdapter: PlayingNowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNowPlayingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerMovieComponents.builder()
            .context(context = requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext().applicationContext, MovieModules::class.java
                )
            ).build()
            .inject(this)
        playingAdapter = PlayingNowAdapter() { dataPlaying ->
            findNavController().navigate(MovieFragmentDirections.actionMovieFragmentToDetailMovieFragment(dataPlaying.id.toString()))
        }
        fetchData()
        observData()
    }

    fun fetchData() {
        nowPlayingViewmodel.getPlayingMovie()
        nowPlayingViewmodel.getPlayingNotPaging()
    }

    fun observData() {
        binding.apply {
            recPlaying.adapter = playingAdapter
            recPlaying.layoutManager = GridLayoutManager(requireContext(),2)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    nowPlayingViewmodel.playingData.collectLatest { pagingData ->

                        pagingData?.let {

                            playingAdapter.submitData(it)
                        }
                    }
                }
                launch {
                    playingAdapter.loadStateFlow.collectLatest { loadState ->
                        if (loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading) {
                            showProgressBar(true)
                        }else {
                            showProgressBar(false)
                            val errorState = when {
                                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                                loadState.prepend is LoadState.Error ->  loadState.prepend as LoadState.Error
                                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                                else -> null
                            }
                            errorState?.let {
                                Toast.makeText(requireContext(), it.error.toString(), Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
            }
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

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recPlaying.adapter = null
        _binding = null
    }


}