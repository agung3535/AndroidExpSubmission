package com.example.moviesubmissionandroidexp.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviesubmissionandroidexp.core.presentation.model.ReviewFavMovieDomainModel
import com.example.moviesubmissionandroidexp.favorite.databinding.ListReviewFavBinding

class ReviewFavAdapter: ListAdapter<ReviewFavMovieDomainModel, ReviewFavAdapter.ReviewFavViewHolder>(
    DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewFavViewHolder {
        return ReviewFavViewHolder(ListReviewFavBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: ReviewFavViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bindData(data)
        }
    }

    inner class ReviewFavViewHolder(val binding: ListReviewFavBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: ReviewFavMovieDomainModel) {
            binding.apply {
                reviewImage.load(data.authorDetail?.avatarPath)
                reviewName.text = data.author
                reviewText.text = data.content
                voteRate.text = data.authorDetail?.rating.toString()
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ReviewFavMovieDomainModel>() {
            override fun areItemsTheSame(
                oldItem: ReviewFavMovieDomainModel,
                newItem: ReviewFavMovieDomainModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ReviewFavMovieDomainModel,
                newItem: ReviewFavMovieDomainModel
            ): Boolean {
                return oldItem.reviewId == newItem.reviewId
            }

        }
    }
}