package com.example.moviesubmissionandroidexp.movie.di

import android.content.Context
import com.example.moviesubmissionandroidexp.di.MovieModules
import com.example.moviesubmissionandroidexp.movie.ui.DetailMovieFragment
import com.example.moviesubmissionandroidexp.movie.ui.MovieFragment
import com.example.moviesubmissionandroidexp.movie.ui.NowPlayingFragment
import com.example.moviesubmissionandroidexp.movie.ui.UpcomingFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [MovieModules::class])
interface MovieComponents {

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(movieModules: MovieModules): Builder
        fun build(): MovieComponents
    }

    fun inject(fragment: NowPlayingFragment)
    fun inject(fragment: UpcomingFragment)
    fun inject(fragment: MovieFragment)
    fun inject(fragment: DetailMovieFragment)


}