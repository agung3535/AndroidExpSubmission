package com.example.moviesubmissionandroidexp.movie.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.moviesubmissionandroidexp.core.presentation.adapter.detailmovie.CastDetailAdapter
import com.example.moviesubmissionandroidexp.core.presentation.adapter.detailmovie.ReviewDetailAdapter
import com.example.moviesubmissionandroidexp.core.presentation.model.DetailMovieModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteMovieModel
import com.example.moviesubmissionandroidexp.di.MovieModules
import com.example.moviesubmissionandroidexp.movie.R
import com.example.moviesubmissionandroidexp.movie.databinding.FragmentDetailMovieBinding
import com.example.moviesubmissionandroidexp.movie.di.DaggerMovieComponents
import com.example.moviesubmissionandroidexp.movie.factory.DetailMovieVMFactory
import com.example.moviesubmissionandroidexp.movie.viewmodel.DetailMovieViewmodel
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


class DetailMovieFragment : Fragment() {

    private var _binding: FragmentDetailMovieBinding? = null
    private val binding: FragmentDetailMovieBinding get() = _binding!!

    @Inject
    lateinit var factory: DetailMovieVMFactory

//    @Inject
//    lateinit var factoryFav: FavoriteVMFactory
//
//    @Inject
//    lateinit var addVMFactory: AddFavoriteVMFactory

    private val detailViewModel: DetailMovieViewmodel by viewModels {
        factory
    }

//    private val favoriteViewmodel: FavoriteListViewmodel by viewModels {
//        factoryFav
//    }
//
//    private val addFavoriteViewmodel: AddFavoriteMovieViewmodel by viewModels {
//        addVMFactory
//    }

    private lateinit var castAdapter: CastDetailAdapter
    private lateinit var reviewAdapter: ReviewDetailAdapter
//    private lateinit var categoryAdapter: FavListCategoryBottomAdapter

    lateinit var args: DetailMovieFragmentArgs

    private var favMovie = arrayListOf<FavoriteMovieModel>()

    private lateinit var detailMovie : DetailMovieModel


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
        _binding = FragmentDetailMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


}