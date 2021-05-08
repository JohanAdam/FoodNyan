package com.nyan.foodie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nyan.domain.entity.restaurant.RestaurantEntity
import com.nyan.foodie.databinding.ListItemRestaurantBinding

class RestaurantsAdapter(val onClickListener: OnItemClickListener): ListAdapter<RestaurantEntity, RestaurantsAdapter.RestaurantsViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<RestaurantEntity>() {
        override fun areItemsTheSame(oldItem: RestaurantEntity, newItem: RestaurantEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: RestaurantEntity, newItem: RestaurantEntity): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantsViewHolder {
        return RestaurantsViewHolder(ListItemRestaurantBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RestaurantsViewHolder, position: Int) {
        val anime = getItem(position)
        //Bind the data to xml.
        holder.bind(anime, onClickListener)
    }

    class RestaurantsViewHolder(private var binding: ListItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: RestaurantEntity, onClickListener: OnItemClickListener) {
            binding.data = restaurant
            binding.executePendingBindings()

            //Set on click listener and data to sent.
            binding.cardRoot.setOnClickListener {
                onClickListener.onClick(restaurant)
            }
        }
    }

}

class OnItemClickListener(val clickListener: (restaurantItem: RestaurantEntity) -> Unit) {
    fun onClick(restaurant: RestaurantEntity) = clickListener(restaurant)
}