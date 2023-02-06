package com.example.moviesubmissionandroidexp.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteMovieModel
import com.example.moviesubmissionandroidexp.core.utils.ConstVal
import com.example.moviesubmissionandroidexp.favorite.databinding.FavoriteMovieItemBinding

class FavListFavMovieAdapter(val itemClick: (FavoriteMovieModel) -> Unit): ListAdapter<FavoriteMovieModel, FavListFavMovieAdapter.FavListMovieViewHolder>(
    DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavListMovieViewHolder {
        return FavListMovieViewHolder(FavoriteMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FavListMovieViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bindData(data)
            holder.itemView.setOnClickListener {
                itemClick(data)
            }
        }
    }

    inner class FavListMovieViewHolder(val binding: FavoriteMovieItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: FavoriteMovieModel) {
            binding.apply {
                imageMovie.load(ConstVal.IMAGE_URL + data.backdropPath)
                titleMovie.text = data.title
                popularText.text = data.popularity.toString()
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteMovieModel>() {
            override fun areItemsTheSame(
                oldItem: FavoriteMovieModel,
                newItem: FavoriteMovieModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: FavoriteMovieModel,
                newItem: FavoriteMovieModel
            ): Boolean {
                return oldItem.movieId == newItem.movieId
            }

        }
    }
}