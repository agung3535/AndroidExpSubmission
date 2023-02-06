package com.example.moviesubmissionandroidexp.favorite.viewmodel.addfavorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesubmissionandroidexp.core.domain.addfavoritemovie.IAddFavoriteMovieUsecase
import com.example.moviesubmissionandroidexp.core.presentation.model.CastDomainModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoritListCategoryModel
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteMovieModel
import com.example.moviesubmissionandroidexp.core.presentation.model.ReviewDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddFavoriteMovieViewmodel(
    private val addFavoriteMovieUsecase: IAddFavoriteMovieUsecase,
): ViewModel() {

    val resultAddCategory: MutableStateFlow<Long?> = MutableStateFlow(null)
    val resultDeleteCategory: MutableStateFlow<Int?> = MutableStateFlow(null)
    val resultAdd: MutableStateFlow<Long?> = MutableStateFlow(null)
    val resultAddCast: MutableStateFlow<List<Long>?> = MutableStateFlow(null)
    val resultAddReview: MutableStateFlow<List<Long>?> = MutableStateFlow(null)
    val resultDelete: MutableStateFlow<Int?> = MutableStateFlow(null)
    val castModel = ArrayList<CastDomainModel>()
    val reviewModel = ArrayList<ReviewDomainModel>()
    private var _fkId: Long = -1L

    fun addCategory(data: FavoritListCategoryModel) = viewModelScope.launch {
        resultAddCategory.value = null
        addFavoriteMovieUsecase.addCategoryFavorite(data).collectLatest {
            resultAddCategory.value = it
        }
    }

    fun addFavoriteMovie(data: FavoriteMovieModel) = viewModelScope.launch {
        resultAdd.value = null
        withContext(Dispatchers.IO) {
            _fkId = addFavoriteMovieUsecase.addFavorite(data)
            addFavoriteCast(castModel)
        }

    }

    fun addFavoriteCast(favCast: List<CastDomainModel>) = viewModelScope.launch {
        addFavoriteMovieUsecase.addCastFavoriteMovie(favCast, _fkId.toInt()).collect {
            resultAddCast.value = it
        }
    }

    fun addFavoriteReview(favReview: List<ReviewDomainModel>) = viewModelScope.launch {
        addFavoriteMovieUsecase.addReviewFavoriteMovie(favReview, _fkId.toInt()).collect {
            resultAddReview.value = it
        }
    }

    fun deleteCategory(data: FavoritListCategoryModel) = viewModelScope.launch {
        addFavoriteMovieUsecase.deleteCategoryFavorite(data).collectLatest {
            resultDeleteCategory.value = it
        }
    }

    fun deleteFavMovie(data: List<FavoriteMovieModel>) = viewModelScope.launch {
        Log.d("dataaa","deleted vm = $data")
        addFavoriteMovieUsecase.deleteFavorite(data).collect {
            Log.d("dataaa","delete movie result = ${it}")
            resultDelete.value = it
        }
    }

    fun addTempDelete(data: FavoriteMovieModel) = viewModelScope.launch {
        addFavoriteMovieUsecase.addTempDelete(data).collect {

        }
    }


}