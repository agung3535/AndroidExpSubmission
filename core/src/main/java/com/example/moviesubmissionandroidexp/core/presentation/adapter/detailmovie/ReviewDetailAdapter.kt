package com.example.moviesubmissionandroidexp.core.presentation.adapter.detailmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviesubmissionandroidexp.core.databinding.ReviewItemBinding
import com.example.moviesubmissionandroidexp.core.presentation.model.ReviewDomainModel

class ReviewDetailAdapter: ListAdapter<ReviewDomainModel, ReviewDetailAdapter.ReviewViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(ReviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bindData(data)
        }
    }

    inner class ReviewViewHolder(val binding: ReviewItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: ReviewDomainModel) {
            binding.apply {
                reviewImage.load(data.authorDetail?.avatarPath)
                reviewName.text = data.author
                reviewText.text = data.content
                voteRate.text = data.authorDetail?.rating.toString()
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ReviewDomainModel>() {
            override fun areItemsTheSame(
                oldItem: ReviewDomainModel,
                newItem: ReviewDomainModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ReviewDomainModel,
                newItem: ReviewDomainModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}