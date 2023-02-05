package com.example.moviesubmissionandroidexp.core.presentation.adapter.upcmingmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviesubmissionandroidexp.core.databinding.PlayingMovieItemBinding
import com.example.moviesubmissionandroidexp.core.presentation.model.UpcomingModel
import com.example.moviesubmissionandroidexp.core.utils.ConstVal

class UpcomingMovieAdapter(val itemClick: (UpcomingModel) -> Unit): ListAdapter<UpcomingModel, UpcomingMovieAdapter.UpcomingViewHolder>(
    DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        return UpcomingViewHolder(PlayingMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        val data = getItem(position)
        if(data != null) {
            holder.bindData(data)
            holder.itemView.setOnClickListener {
                itemClick(data)
            }
        }
    }


    inner class UpcomingViewHolder(val binding: PlayingMovieItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: UpcomingModel) {
            binding.apply {
                imageMovie.load(ConstVal.IMAGE_URL + data.backdropPath)
                titleMovie.text = data.title
                popularText.text = data.popularity.toString()
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UpcomingModel>() {
            override fun areItemsTheSame(oldItem: UpcomingModel, newItem: UpcomingModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: UpcomingModel,
                newItem: UpcomingModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }


}