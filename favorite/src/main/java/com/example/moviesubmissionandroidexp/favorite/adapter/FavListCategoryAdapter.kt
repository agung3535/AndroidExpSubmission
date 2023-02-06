package com.example.moviesubmissionandroidexp.favorite.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoriteCategoryWithPreviewModel
import com.example.moviesubmissionandroidexp.favorite.databinding.ListCategoryBinding

class FavListCategoryAdapter(val itemClick: (FavoriteCategoryWithPreviewModel) -> Unit): ListAdapter<FavoriteCategoryWithPreviewModel, FavListCategoryAdapter.FavListViewHolder>(
    DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavListViewHolder {
        return FavListViewHolder(ListCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false), parent.context)
    }

    override fun onBindViewHolder(holder: FavListViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bindData(data)
            holder.itemView.setOnClickListener {
                itemClick(data)
            }
        }
    }


    inner class FavListViewHolder(val binding: ListCategoryBinding, val context: Context): RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: FavoriteCategoryWithPreviewModel) {
            binding.apply {
                titleList.text = data.favCategory.categoryName
                jumlah.text = data.movie.size.toString() + " movies"
                val adapter = PreviewFavAdapter()
                recPreview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                recPreview.adapter = adapter
                if (data.movie.size > 3) {
                    adapter.submitList(data.movie.take(3))
                }else {
                    adapter.submitList(data.movie)
                }

            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<FavoriteCategoryWithPreviewModel>() {
            override fun areItemsTheSame(
                oldItem: FavoriteCategoryWithPreviewModel,
                newItem: FavoriteCategoryWithPreviewModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: FavoriteCategoryWithPreviewModel,
                newItem: FavoriteCategoryWithPreviewModel
            ): Boolean {
                return oldItem.favCategory.favCategoryMovieId == newItem.favCategory.favCategoryMovieId
            }

        }
    }


}