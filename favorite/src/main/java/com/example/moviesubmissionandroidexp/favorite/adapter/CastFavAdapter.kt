package com.example.moviesubmissionandroidexp.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviesubmissionandroidexp.core.presentation.model.CastFavMovieDomainModel
import com.example.moviesubmissionandroidexp.core.utils.ConstVal
import com.example.moviesubmissionandroidexp.favorite.databinding.ListCastFavBinding

class CastFavAdapter: ListAdapter<CastFavMovieDomainModel, CastFavAdapter.CastFavViewHolder>(
    DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastFavViewHolder {
        return CastFavViewHolder(ListCastFavBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: CastFavViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bindData(data)
        }
    }

    inner class CastFavViewHolder(val binding: ListCastFavBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: CastFavMovieDomainModel) {
            binding.apply {
                castImage.load(ConstVal.IMAGE_URL + data.profilePath)
                castName.text = data.name
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CastFavMovieDomainModel>() {
            override fun areItemsTheSame(
                oldItem: CastFavMovieDomainModel,
                newItem: CastFavMovieDomainModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: CastFavMovieDomainModel,
                newItem: CastFavMovieDomainModel
            ): Boolean {
                return oldItem.castId == newItem.castId
            }

        }
    }


}