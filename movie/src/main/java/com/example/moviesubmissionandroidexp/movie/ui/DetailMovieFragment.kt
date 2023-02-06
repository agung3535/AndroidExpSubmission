package com.example.moviesubmissionandroidexp.movie.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviesubmissionandroidexp.core.entities.Resource
import com.example.moviesubmissionandroidexp.core.presentation.adapter.detailmovie.CastDetailAdapter
import com.example.moviesubmissionandroidexp.core.presentation.adapter.detailmovie.ReviewDetailAdapter
import com.example.moviesubmissionandroidexp.core.presentation.model.DetailMovieModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoritListCategoryModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteMovieModel
import com.example.moviesubmissionandroidexp.core.utils.ConstVal
import com.example.moviesubmissionandroidexp.di.MovieModules
import com.example.moviesubmissionandroidexp.favorite.adapter.FavListCategoryBottomAdapter
import com.example.moviesubmissionandroidexp.favorite.factory.AddFavoriteVMFactory
import com.example.moviesubmissionandroidexp.favorite.factory.FavoriteVMFactory
import com.example.moviesubmissionandroidexp.favorite.viewmodel.addfavorite.AddFavoriteMovieViewmodel
import com.example.moviesubmissionandroidexp.favorite.viewmodel.favoritelist.FavoriteListViewmodel
import com.example.moviesubmissionandroidexp.movie.R
import com.example.moviesubmissionandroidexp.movie.databinding.FragmentDetailMovieBinding
import com.example.moviesubmissionandroidexp.movie.di.DaggerMovieComponents
import com.example.moviesubmissionandroidexp.movie.factory.DetailMovieVMFactory
import com.example.moviesubmissionandroidexp.movie.viewmodel.DetailMovieViewmodel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.moviesubmissionandroidexp.R as Rapp

class DetailMovieFragment : Fragment() {

    private var _binding: FragmentDetailMovieBinding? = null
    private val binding: FragmentDetailMovieBinding get() = _binding!!

    @Inject
    lateinit var factory: DetailMovieVMFactory

    @Inject
    lateinit var factoryFav: FavoriteVMFactory

    @Inject
    lateinit var addVMFactory: AddFavoriteVMFactory

    private val detailViewModel: DetailMovieViewmodel by viewModels {
        factory
    }

    private val favoriteViewmodel: FavoriteListViewmodel by viewModels {
        factoryFav
    }

    private val addFavoriteViewmodel: AddFavoriteMovieViewmodel by viewModels {
        addVMFactory
    }

    private lateinit var castAdapter: CastDetailAdapter
    private lateinit var reviewAdapter: ReviewDetailAdapter
    private lateinit var categoryAdapter: FavListCategoryBottomAdapter

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        castAdapter = CastDetailAdapter()
        reviewAdapter = ReviewDetailAdapter()
        categoryAdapter = FavListCategoryBottomAdapter() { data ->
            if (favMovie.size == 0) {
                favMovie.add(FavoriteMovieModel(title = detailMovie.title, backdropPath = detailMovie.backdropPath, overview = detailMovie.overview,
                    popularity = detailMovie.popularity, voteAverage = detailMovie.voteAverage, originalLanguage = detailMovie.originalLanguage, favCategoryMovieId = data.favCategoryMovieId, movieId = detailMovie.id))
            }else {
                var cek = favMovie.filter { fav -> fav.favCategoryMovieId == data.favCategoryMovieId}
                if (cek.size != 0) {
                    favMovie.remove(cek.single())
                }else {
                    favMovie.add(FavoriteMovieModel(title = detailMovie.title, backdropPath = detailMovie.backdropPath, overview = detailMovie.overview,
                        popularity = detailMovie.popularity, voteAverage = detailMovie.voteAverage, originalLanguage = detailMovie.originalLanguage, favCategoryMovieId = data.favCategoryMovieId, movieId = detailMovie.id))
                }
            }
        }
        initUI()
        fetchData()
        observeData()
        binding.favBtn.setOnClickListener {
            showDialog()
        }
        binding.unFavBtn.setOnClickListener {
            deleteFavorite(favMovie)
        }
    }

    private fun showDialog() {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(Rapp.layout.list_category_favorite_bottom)
        val recCategory = dialog.findViewById<RecyclerView>(Rapp.id.recCategory)
        val btnCreate = dialog.findViewById<Button>(Rapp.id.btnAddList)
        val btnSave = dialog.findViewById<Button>(Rapp.id.btnSave)
        recCategory?.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
        recCategory?.adapter = categoryAdapter

        btnCreate?.setOnClickListener {
            addFavorite()
        }
        btnSave?.setOnClickListener {
            saveFavorite(favMovie)
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun saveFavorite(data: List<FavoriteMovieModel>,) {
        showPrograss(true)
        for (i in data) {
            lifecycleScope.launch {
                addFavoriteViewmodel.addFavoriteMovie(i)
            }

        }
    }

    private fun addFavorite() {
        val dialogNew = BottomSheetDialog(requireContext())
        dialogNew.setContentView(Rapp.layout.add_category_layout)
        val btnCreate = dialogNew.findViewById<Button>(Rapp.id.btnCreate)
        val edtNameList = dialogNew.findViewById<EditText>(Rapp.id.edtListName)
        btnCreate?.setOnClickListener {
            val category = FavoritListCategoryModel(categoryName = edtNameList?.text.toString())
            addFavoriteViewmodel.addCategory(category)
            dialogNew.dismiss()
        }
        dialogNew.show()
    }

    private fun deleteFavorite(data: List<FavoriteMovieModel>) {
        addFavoriteViewmodel.deleteFavMovie(data)
        favMovie.clear()
    }

    private fun fetchData() {
        args = DetailMovieFragmentArgs.fromBundle(requireArguments())
        detailViewModel.getMovieDetail(args.movieid.toString())
        detailViewModel.getCastMovie(args.movieid.toString())
        detailViewModel.getReviewMovie(args.movieid.toString())
        favoriteViewmodel.getCategoryList()
        args.movieid?.let { favoriteViewmodel.cekFavorite(it.toInt()) }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    detailViewModel.movieDetail.collect { data ->
                        when(data) {
                            is Resource.Error -> {
                                binding.progressBar.visibility = View.GONE
                            }
                            is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                            is Resource.Success -> {
                                binding.apply {
                                    detailMovie = data.data!!
                                    progressBar.visibility = View.GONE
                                    textOverview.text = data.data?.overview
                                    imageMovie.load(ConstVal.IMAGE_URL + data.data?.backdropPath)
                                    titleMovie.text = data.data?.title
                                    textGenre.text = data.data?.genres?.joinToString {
                                        it.name + ""
                                    }
                                }

                            }
                            null -> {
                                binding.progressBar.visibility = View.GONE
                            }
                        }
                    }
                }
                launch {
                    detailViewModel.castList.collect { data ->
                        when(data) {
                            is Resource.Error -> {
                                binding.apply {
                                    progressCast.visibility = View.GONE
                                    recCast.visibility = View.GONE
                                }
                            }
                            is Resource.Loading -> binding.progressCast.visibility = View.VISIBLE
                            is Resource.Success -> {
                                binding.apply {
                                    val dataRec = data.data
                                    if (dataRec != null) {

                                        progressCast.visibility = View.GONE
                                        if (dataRec.isNotEmpty()) {
                                            emptyCast.root.visibility = View.GONE
                                            recCast.visibility = View.VISIBLE
                                            castAdapter.submitList(data.data)
                                            if (addFavoriteViewmodel.castModel.size != 0) {
                                                addFavoriteViewmodel.castModel.clear()
                                                addFavoriteViewmodel.castModel.addAll(dataRec)
                                            }else {
                                                addFavoriteViewmodel.castModel.addAll(dataRec)
                                            }
                                        }else {
                                            emptyCast.root.visibility = View.VISIBLE
                                            recCast.visibility = View.GONE
                                        }
                                    }else {

                                    }

                                }
                            }
                            null -> {
                                binding.progressCast.visibility = View.GONE
                            }
                        }
                    }
                }
                launch {
                    detailViewModel.reviewMovie.collect { data ->
                        when(data) {
                            is Resource.Error -> {
                                binding.apply {
                                    progressReview.visibility = View.GONE
                                    recReview.visibility = View.GONE
                                }
                            }
                            is Resource.Loading -> binding.progressReview.visibility = View.VISIBLE
                            is Resource.Success -> {
                                binding.apply {
                                    progressReview.visibility = View.GONE
                                    val dataRec = data.data
                                    if (dataRec != null) {
                                        if (dataRec.isNotEmpty()) {
                                            emptyViewReview.root.visibility = View.GONE
                                            recReview.visibility = View.VISIBLE
                                            reviewAdapter.submitList(data.data)
                                            if (addFavoriteViewmodel.reviewModel.size != 0) {
                                                addFavoriteViewmodel.reviewModel.clear()
                                                addFavoriteViewmodel.reviewModel.addAll(dataRec)
                                            }else {
                                                addFavoriteViewmodel.reviewModel.addAll(dataRec)
                                            }
                                        }else {
                                            emptyViewReview.root.visibility = View.VISIBLE
                                            recReview.visibility = View.GONE
                                        }
                                    }else {
                                        emptyViewReview.root.visibility = View.VISIBLE
                                        recReview.visibility = View.GONE
                                    }

                                }
                            }
                            null -> {
                                binding.progressReview.visibility = View.GONE
                            }
                        }
                    }
                }
                launch {
                    favoriteViewmodel.categoryListName.collectLatest {data ->
                        categoryAdapter.submitList(data)
                    }
                }
                launch {
                    addFavoriteViewmodel.resultAdd.collect {
                        if (it != null) {
                            showPrograss(false)
                        }
                    }
                }
                launch {
                    favoriteViewmodel.isFavorite.collect { collectData ->
                        showPrograss(false)
                        if (collectData != null){
                            favMovie.addAll(collectData)
                            binding.unFavBtn.visibility = View.VISIBLE
                            binding.favBtn.visibility = View.GONE
                        }else {
                            binding.unFavBtn.visibility = View.GONE
                            binding.favBtn.visibility = View.VISIBLE
                        }
                    }
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
        binding.apply {
            recCast.layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL,false)
            recReview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            recCast.adapter = castAdapter
            recReview.adapter = reviewAdapter
        }
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(Rapp.id.bottomNav)
        bottomNav.visibility = View.GONE
    }

    override fun onDetach() {
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(Rapp.id.bottomNav)
        bottomNav.visibility = View.VISIBLE
        val supportActionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
        if (supportActionBar != null) supportActionBar.hide()
        super.onDetach()
    }

    override fun onAttach(context: Context) {
        val supportActionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
        if (supportActionBar != null) supportActionBar.show()
        super.onAttach(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.apply {
            recCast.adapter = null
            recReview.adapter = null
        }
        _binding = null
    }


}