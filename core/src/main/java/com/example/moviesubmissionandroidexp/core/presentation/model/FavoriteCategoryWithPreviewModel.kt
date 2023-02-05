package com.example.moviesubmissionandroidexp.core.presentation.model

data class FavoriteCategoryWithPreviewModel(
    val favCategory: FavoritListCategoryModel,
    val movie: List<FavoriteMovieModel>
)