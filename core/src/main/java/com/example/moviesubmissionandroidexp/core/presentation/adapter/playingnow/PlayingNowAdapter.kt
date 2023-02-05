package com.example.moviesubmissionandroidexp.core.presentation.adapter.playingnow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviesubmissionandroidexp.core.databinding.PlayingMovieItemBinding
import com.example.moviesubmissionandroidexp.core.presentation.model.PlayingNowModel
import com.example.moviesubmissionandroidexp.core.utils.ConstVal

class PlayingNowAdapter(val itemClick: (PlayingNowModel) -> Unit): PagingDataAdapter<PlayingNowModel, PlayingNowAdapter.PlayingViewHolder>(
    DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: PlayingViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bindData(data)
            holder.itemView.setOnClickListener {
                itemClick(data)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayingViewHolder {
        return PlayingViewHolder(PlayingMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    inner class PlayingViewHolder(val binding: PlayingMovieItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: PlayingNowModel) {
            binding.apply {
                imageMovie.load(ConstVal.IMAGE_URL + data.backdropPath)
                titleMovie.text = data.title
                popularText.text = data.popularity.toString()
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<PlayingNowModel>() {
            override fun areItemsTheSame(
                oldItem: PlayingNowModel,
                newItem: PlayingNowModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: PlayingNowModel,
                newItem: PlayingNowModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }


}