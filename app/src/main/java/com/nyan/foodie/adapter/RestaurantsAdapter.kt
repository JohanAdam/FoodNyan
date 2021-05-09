package com.nyan.foodie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nyan.foodie.binding.model.restaurant.Restaurant as RestaurantBinding
import com.nyan.foodie.databinding.ListItemRestaurantBinding

class RestaurantsAdapter(val onClickListener: OnItemClickListener): ListAdapter<RestaurantBinding, RestaurantsAdapter.RestaurantsViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<RestaurantBinding>() {
        override fun areItemsTheSame(oldItem: RestaurantBinding, newItem: RestaurantBinding): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: RestaurantBinding, newItem: RestaurantBinding): Boolean {
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
        fun bind(restaurant: RestaurantBinding, onClickListener: OnItemClickListener) {
            binding.data = restaurant
            binding.executePendingBindings()

            //Set on click listener and data to sent.
            binding.cardRoot.setOnClickListener {
                onClickListener.onClick(restaurant)
            }
        }
    }

}

class OnItemClickListener(val clickListener: (restaurantItem: RestaurantBinding) -> Unit) {
    fun onClick(restaurant: RestaurantBinding) = clickListener(restaurant)
}