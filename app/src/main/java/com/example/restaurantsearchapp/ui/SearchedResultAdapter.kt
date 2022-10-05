package com.example.restaurantsearchapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantsearchapp.R
import com.example.restaurantsearchapp.databinding.ItemViewSearchedResultBinding
import com.example.restaurantsearchapp.models.RestaurantsItem

class SearchedResultAdapter : RecyclerView.Adapter<SearchedResultAdapter.MyViewHolder>() {

    private var resultList = emptyList<RestaurantsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemViewSearchedResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    private fun getItem(position: Int): RestaurantsItem {
        return resultList[position]
    }

    fun submitList(list: List<RestaurantsItem>) {
        val diffUtil =
            DiffUtilCallBack(resultList, list)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        resultList = list
        diffUtilResult.dispatchUpdatesTo(this)
    }


    class MyViewHolder(
        private val binding: ItemViewSearchedResultBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RestaurantsItem) {
            binding.type.text = "Restaurant"
            binding.imageView.setImageResource(R.drawable.ic_restaurant)
            binding.name.text = item.name
        }

    }

    class DiffUtilCallBack(
        private val oldList: List<RestaurantsItem>,
        private val newList: List<RestaurantsItem>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
                    && oldList[oldItemPosition].name == newList[newItemPosition].name
        }

    }
}