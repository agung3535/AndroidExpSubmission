package com.example.moviesubmissionandroidexp.favorite.ui

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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoritListCategoryModel
import com.example.moviesubmissionandroidexp.di.FavoriteModules
import com.example.moviesubmissionandroidexp.R
import com.example.moviesubmissionandroidexp.favorite.adapter.FavListCategoryAdapter
import com.example.moviesubmissionandroidexp.favorite.databinding.FragmentFavoriteBinding
import com.example.moviesubmissionandroidexp.favorite.di.DaggerFavoriteMovieComponents
import com.example.moviesubmissionandroidexp.favorite.factory.AddFavoriteVMFactory
import com.example.moviesubmissionandroidexp.favorite.factory.FavoriteVMFactory
import com.example.moviesubmissionandroidexp.favorite.viewmodel.addfavorite.AddFavoriteMovieViewmodel
import com.example.moviesubmissionandroidexp.favorite.viewmodel.favoritelist.FavoriteListViewmodel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.launch
import javax.inject.Inject


class FavoriteFragment : Fragment() {

    @Inject
    lateinit var factory: FavoriteVMFactory
    @Inject
    lateinit var addVMFactory: AddFavoriteVMFactory

    private val favoriteViewmodel: FavoriteListViewmodel by viewModels {
        factory
    }

    private val addFavoriteMovieViewmodel: AddFavoriteMovieViewmodel by viewModels {
        addVMFactory
    }

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var favoriteAdapter: FavListCategoryAdapter

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
        _binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        fetchData()
        observeData()
    }


    private fun fetchData() {
        favoriteViewmodel.getCategory()
    }

    private fun observeData() {
        lifecycleScope.launch {
            favoriteViewmodel.categoryList.collect { observe ->
                val data = observe
                if (data != null) {
                    if (data.isNotEmpty()) {
                        favoriteAdapter.submitList(data)
                        showPrograss(false)
                        setEmpty(false)
                    }else {
                        setEmpty(true)
                        showPrograss(false)
                    }
                }

            }
            addFavoriteMovieViewmodel.resultAddCategory.collect { result ->
                val data = result
                if (data != null) {
                    showPrograss(false)
                }
            }
        }
    }

    private fun initUI() {
        favoriteAdapter = FavListCategoryAdapter { data ->
            findNavController().navigate(FavoriteFragmentDirections.actionFavoriteFragmentToListFavoriteFragment(data.favCategory.categoryName,data.favCategory.favCategoryMovieId))
        }
        binding.apply {
            recFavoriteList.layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL,false)
            recFavoriteList.adapter = favoriteAdapter

            btnAddList.setOnClickListener {
                showDialog()
            }
        }
    }

    private fun showDialog() {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.add_category_layout)
        val btnCreate = dialog.findViewById<Button>(R.id.btnCreate)
        val edtNameList = dialog.findViewById<EditText>(R.id.edtListName)

        btnCreate?.setOnClickListener {
            showPrograss(true)
            val category = FavoritListCategoryModel(categoryName = edtNameList?.text.toString())
            addFavoriteMovieViewmodel.addCategory(category)
        }
        dialog.show()
    }

    private fun setEmpty(isEmpty: Boolean) {
        if (isEmpty) {
            binding.recFavoriteList.visibility = View.GONE
            binding.emptyView.root.visibility = View.VISIBLE
        }else {
            binding.recFavoriteList.visibility = View.VISIBLE
            binding.emptyView.root.visibility = View.GONE
        }
    }

    override fun onAttach(context: Context) {
        val supportActionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
        if (supportActionBar != null) supportActionBar.hide()

        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.visibility = View.VISIBLE
        super.onAttach(context)
    }

    private fun showPrograss(show: Boolean){
        if(show) {
            binding.progressBar.visibility = View.VISIBLE
        }else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recFavoriteList.adapter = null
        _binding = null
    }


}