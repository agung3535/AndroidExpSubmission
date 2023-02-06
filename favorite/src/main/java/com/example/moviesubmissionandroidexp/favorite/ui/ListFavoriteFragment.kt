package com.example.moviesubmissionandroidexp.favorite.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesubmissionandroidexp.di.FavoriteModules
import com.example.moviesubmissionandroidexp.favorite.R
import com.example.moviesubmissionandroidexp.favorite.adapter.FavListFavMovieAdapter
import com.example.moviesubmissionandroidexp.favorite.databinding.FragmentListFavoriteBinding
import com.example.moviesubmissionandroidexp.favorite.di.DaggerFavoriteMovieComponents
import com.example.moviesubmissionandroidexp.favorite.factory.FavoriteVMFactory
import com.example.moviesubmissionandroidexp.favorite.viewmodel.favoritelist.FavoriteListViewmodel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.moviesubmissionandroidexp.R as Rapp


class ListFavoriteFragment : Fragment() {

    private lateinit var binding: FragmentListFavoriteBinding

    private lateinit var favAdapter: FavListFavMovieAdapter

    private lateinit var args: ListFavoriteFragmentArgs

    @Inject
    lateinit var favFactor: FavoriteVMFactory

    private val favViewmodel: FavoriteListViewmodel by viewModels {
        favFactor
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        DaggerFavoriteMovieComponents.builder()
            .context(context= requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireActivity().applicationContext, FavoriteModules::class.java
                )
            ).build()
            .inject(this)
        binding = FragmentListFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favAdapter = FavListFavMovieAdapter() { data ->

            val action = ListFavoriteFragmentDirections.actionListFavoriteFragmentToDetailFavoriteFragment3(data.movieId)

            findNavController().navigate(action)
        }

        args = ListFavoriteFragmentArgs.fromBundle(requireArguments())
        initUI()
        fetchData()
        observeData()
        binding.titleFavorite.setOnClickListener {
            val action = ListFavoriteFragmentDirections.actionListFavoriteFragmentToDetailFavoriteFragment3(1)

            findNavController().navigate(action)
        }
    }

    private fun fetchData() {
        favViewmodel.getFavoriteMovie(args.categoryId)
    }

    private fun observeData() {
        lifecycleScope.launch {
            favViewmodel.favoriteData.collect { collectData ->
                favAdapter.submitList(collectData)
            }
        }
    }

    private fun initUI() {

        binding.apply {
            titleFavorite.text = args.categoryTitle.toString()
            recFavorite.layoutManager = GridLayoutManager(requireContext(),2)
            recFavorite.adapter = favAdapter
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(Rapp.id.bottomNav)
        bottomNav.visibility = View.GONE
    }

    override fun onDetach() {
        super.onDetach()
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(Rapp.id.bottomNav)
        bottomNav.visibility = View.VISIBLE
    }


}