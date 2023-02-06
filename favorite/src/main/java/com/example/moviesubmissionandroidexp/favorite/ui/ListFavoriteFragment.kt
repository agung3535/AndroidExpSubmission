package com.example.moviesubmissionandroidexp.favorite.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteMovieModel
import com.example.moviesubmissionandroidexp.core.utils.Mapper.toFavDomain
import com.example.moviesubmissionandroidexp.core.utils.Mapper.toTempEnt
import com.example.moviesubmissionandroidexp.di.FavoriteModules
import com.example.moviesubmissionandroidexp.favorite.R
import com.example.moviesubmissionandroidexp.favorite.adapter.FavListFavMovieAdapter
import com.example.moviesubmissionandroidexp.favorite.databinding.FragmentListFavoriteBinding
import com.example.moviesubmissionandroidexp.favorite.di.DaggerFavoriteMovieComponents
import com.example.moviesubmissionandroidexp.favorite.factory.FavoriteVMFactory
import com.example.moviesubmissionandroidexp.favorite.viewmodel.favoritelist.FavoriteListViewmodel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
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
        args = ListFavoriteFragmentArgs.fromBundle(requireArguments())
        favAdapter = FavListFavMovieAdapter() { data ->

            val action = ListFavoriteFragmentDirections.actionListFavoriteFragmentToDetailFavoriteFragment3(data.movieId,args.categoryTitle,args.categoryId)

            findNavController().navigate(action)
        }


        initUI()


    }

    override fun onResume() {
        super.onResume()
        fetchData()
        observeData()
    }

    private fun setEmpty(isEmpty: Boolean) {
        if (isEmpty) {
            binding?.recFavorite?.visibility = View.GONE
            binding?.emptyView?.root?.visibility = View.VISIBLE
        }else {
            binding?.recFavorite?.visibility = View.VISIBLE
            binding?.emptyView?.root?.visibility = View.GONE
        }
    }

    private fun fetchData() {
        favViewmodel.getTempFav()
        lifecycleScope.launch {
            favViewmodel.tempDeleteFav.collectLatest {
                val data = it
                if (data != null) {
                    if (data.isEmpty()) {
                        favViewmodel.getFavoriteMovie(args.categoryId)
                    }else {
                        favViewmodel.deleteFavMovie(data)
                        favViewmodel.deleteTempFav(data.first().toTempEnt())
                        favViewmodel.cekFavorite(data.first().movieId)
                    }
                }else {
                    favViewmodel.getFavoriteMovie(args.categoryId)
                }
            }
        }

    }

    private fun observeData() {
        lifecycleScope.launch {
            favViewmodel.favoriteData.collect { collectData ->
                if (collectData?.isNotEmpty() == true) {
                    setEmpty(false)
                    favAdapter.submitList(collectData)
                }else {
                    setEmpty(true)
                }

            }
        }

        lifecycleScope.launch {
            favViewmodel.isFavorite.collect { collectData ->
                if (collectData == null){
                    favViewmodel.getFavoriteMovie(args.categoryId)
                }
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