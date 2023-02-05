package com.example.moviesubmissionandroidexp.core.presentation.adapter.detailmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviesubmissionandroidexp.core.databinding.CastItemLayoutBinding
import com.example.moviesubmissionandroidexp.core.presentation.model.CastDomainModel
import com.example.moviesubmissionandroidexp.core.utils.ConstVal

class CastDetailAdapter: ListAdapter<CastDomainModel, CastDetailAdapter.CastViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder(CastItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bindData(data)
        }
    }


    inner class CastViewHolder(val binding: CastItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: CastDomainModel) {
            binding.apply {
                castImage.load(ConstVal.IMAGE_URL + data.profilePath)
                castName.text = data.name
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CastDomainModel>() {
            override fun areItemsTheSame(
                oldItem: CastDomainModel,
                newItem: CastDomainModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: CastDomainModel,
                newItem: CastDomainModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }


}