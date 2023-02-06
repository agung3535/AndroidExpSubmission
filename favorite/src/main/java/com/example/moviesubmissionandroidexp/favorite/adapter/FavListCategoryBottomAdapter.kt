package com.example.moviesubmissionandroidexp.favorite.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesubmissionandroidexp.core.presentation.model.FavoritListCategoryModel
import com.example.moviesubmissionandroidexp.favorite.databinding.ItemCategoryBinding

class FavListCategoryBottomAdapter(val itemClick: (FavoritListCategoryModel) -> Unit): ListAdapter<FavoritListCategoryModel, FavListCategoryBottomAdapter.FavListBottomViewHolder>(
    DIFF_CALLBACK) {

    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavListBottomViewHolder {
        return FavListBottomViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FavListBottomViewHolder, position: Int) {
        val data = getItem(position)
        if(data != null) {
            holder.bindData(data,position)
//            holder.binding.txtCategory.setOnCheckedChangeListener { compoundButton, b ->
//                if (b) {
//                    selectedPosition = holder.bindingAdapterPosition
//                    notifyDataSetChanged()
//                }
//            }
            holder.itemView.setOnClickListener {

            }
            holder.binding.txtCategory.setOnCheckedChangeListener { compoundButton, b ->
                itemClick(data)
                if (b) {
                    holder.binding.txtCategory.isChecked = b
                }
            }

        }
    }



    inner class FavListBottomViewHolder(val binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: FavoritListCategoryModel,position: Int) {
            binding.apply {
//                txtCategory.isChecked = position == selectedPosition
                txtCategory.text = data.categoryName
            }
        }


    }

    companion object {
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<FavoritListCategoryModel>() {
            override fun areItemsTheSame(
                oldItem: FavoritListCategoryModel,
                newItem: FavoritListCategoryModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: FavoritListCategoryModel,
                newItem: FavoritListCategoryModel
            ): Boolean {
                return oldItem.favCategoryMovieId == newItem.favCategoryMovieId
            }

        }
    }


}