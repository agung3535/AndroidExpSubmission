package com.example.moviesubmissionandroidexp.favorite.di

import android.content.Context
import com.example.moviesubmissionandroidexp.di.FavoriteModules
import com.example.moviesubmissionandroidexp.favorite.ui.DetailFavoriteFragment
import com.example.moviesubmissionandroidexp.favorite.ui.FavoriteFragment
import com.example.moviesubmissionandroidexp.favorite.ui.ListFavoriteFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModules::class])
interface FavoriteMovieComponents {

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModules: FavoriteModules): Builder
        fun build(): FavoriteMovieComponents
    }

    fun inject(fragment: FavoriteFragment)
    fun inject(fragment: ListFavoriteFragment)
    fun inject(fragment: DetailFavoriteFragment)


}