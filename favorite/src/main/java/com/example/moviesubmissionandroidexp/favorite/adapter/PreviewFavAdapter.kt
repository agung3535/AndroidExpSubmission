package com.example.moviesubmissionandroidexp.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteMovieModel
import com.example.moviesubmissionandroidexp.core.utils.ConstVal
import com.example.moviesubmissionandroidexp.favorite.databinding.PrevListItemBinding

class PreviewFavAdapter: ListAdapter<FavoriteMovieModel, PreviewFavAdapter.PreviewViewHolder>(
    DIIF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviewViewHolder {
        return PreviewViewHolder(PrevListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PreviewViewHolder, position: Int) {
        val data = getItem(position)
        if(data != null) {
            holder.bindData(data)
        }
    }


    inner class PreviewViewHolder(val binding: PrevListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: FavoriteMovieModel) {
            binding.apply {
                imagePrev.load(ConstVal.IMAGE_URL + data.backdropPath)
            }
        }
    }


    companion object {
        val DIIF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteMovieModel>() {
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
                return oldItem.favId == newItem.favId
            }
        }
    }


}