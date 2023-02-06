package com.example.moviesubmissionandroidexp.favorite.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteMovieModel
import com.example.moviesubmissionandroidexp.core.utils.ConstVal
import com.example.moviesubmissionandroidexp.di.FavoriteModules
import com.example.moviesubmissionandroidexp.favorite.R
import com.example.moviesubmissionandroidexp.favorite.adapter.CastFavAdapter
import com.example.moviesubmissionandroidexp.favorite.adapter.ReviewFavAdapter
import com.example.moviesubmissionandroidexp.favorite.databinding.FragmentDetailFavoriteBinding
import com.example.moviesubmissionandroidexp.favorite.di.DaggerFavoriteMovieComponents
import com.example.moviesubmissionandroidexp.favorite.factory.AddFavoriteVMFactory
import com.example.moviesubmissionandroidexp.favorite.factory.FavoriteVMFactory
import com.example.moviesubmissionandroidexp.favorite.viewmodel.addfavorite.AddFavoriteMovieViewmodel
import com.example.moviesubmissionandroidexp.favorite.viewmodel.favoritelist.FavoriteListViewmodel
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class DetailFavoriteFragment : Fragment() {

    private lateinit var binding: FragmentDetailFavoriteBinding

    @Inject
    lateinit var factoryFav: FavoriteVMFactory

    @Inject
    lateinit var addFavorite: AddFavoriteVMFactory

    private val favoriteViewmodel: FavoriteListViewmodel by viewModels {
        factoryFav
    }

    private val addFavoriteViewmodel: AddFavoriteMovieViewmodel by viewModels {
        addFavorite
    }

    lateinit var args: DetailFavoriteFragmentArgs

    private lateinit var castFavAdapter: CastFavAdapter

    private lateinit var reviewFavAdapter: ReviewFavAdapter

    private var favMovie = arrayListOf<FavoriteMovieModel>()

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
        binding = FragmentDetailFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        fetchData()
        observeData()
    }

    private fun fetchData() {
        args = DetailFavoriteFragmentArgs.fromBundle(requireArguments())

        favoriteViewmodel.cekFavorite(args.movieId)
    }



    private fun observeData() {
        lifecycleScope.launch {
            favoriteViewmodel.detailFavoriteData.collect { data ->
                var favData = data

                if (favData != null) {
                    favMovie.add(favData.detailMovie)
                    castFavAdapter.submitList(favData.castMovie)
                    reviewFavAdapter.submitList(favData.reviewMovie)
                    binding.apply {
                        imageMovie.load(ConstVal.IMAGE_URL + favData.detailMovie.backdropPath)
                        textOverview.text = favData.detailMovie.overview
                        imageMovie.load(ConstVal.IMAGE_URL + favData.detailMovie.backdropPath)
                        titleMovie.text = favData.detailMovie.title

                        if (favData.castMovie.isEmpty()) {
                            emptyCast.root.visibility = View.VISIBLE
                            recCast.visibility = View.GONE
                        }else {
                            recCast.visibility = View.VISIBLE
                            emptyCast.root.visibility = View.GONE
                        }

                        if (favData.reviewMovie.isEmpty()) {
                            emptyViewReview.root.visibility = View.VISIBLE
                            recReview.visibility = View.GONE
                        }else {
                            emptyViewReview.root.visibility = View.GONE
                            recReview.visibility = View.VISIBLE
                        }
                        progressCast.visibility = View.GONE
                        progressReview.visibility = View.GONE
                    }
                }

            }
        }

        lifecycleScope.launch {
            favoriteViewmodel.isFavorite.collect { collectData ->
                showPrograss(false)
                if (collectData != null){
                    binding.unFavBtn.visibility = View.VISIBLE
                    binding.favBtn.visibility = View.GONE
                    favoriteViewmodel.getDetailFavoriteMovie(args.movieId)
                }else {
                    binding.unFavBtn.visibility = View.GONE
                    binding.favBtn.visibility = View.VISIBLE
                }
            }
        }

    }

    private fun showPrograss(show: Boolean){
        if(show) {
            binding.progressBar.visibility = View.VISIBLE
        }else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun initUI() {
        castFavAdapter = CastFavAdapter()
        reviewFavAdapter = ReviewFavAdapter()
        binding.apply {
            recCast.layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL,false)
            recReview.layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false)
            recReview.adapter = reviewFavAdapter
            recCast.adapter = castFavAdapter

            unFavBtn.setOnClickListener {
                lifecycleScope.launch {
                    val favData = favMovie
                    deleteFavorite(favData)
                    findNavController().popBackStack()
                }

            }
        }
    }

    private fun deleteFavorite(data: List<FavoriteMovieModel>) {
        addFavoriteViewmodel.addTempDelete(data.first())

//        favMovie.clear()
//        favMovie.clear()
    }



}