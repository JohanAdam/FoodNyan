package com.nyan.foodie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nyan.foodie.databinding.ListItemPictureBinding

class PicturesAdapter(private val onClickListener: (String) -> Unit): ListAdapter<String, PicturesAdapter.PicturesViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicturesViewHolder {
        return PicturesViewHolder(
            ListItemPictureBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: PicturesViewHolder, position: Int) {
        val url = getItem(position)
        //Bind the data to xml.
        holder.bind(url, onClickListener)
    }

    class PicturesViewHolder(private var binding: ListItemPictureBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(url: String, onClickListener: (String) -> Unit) {
            binding.imgUrl = url
            binding.executePendingBindings()

            //Set on click listener and data to sent.
            binding.cardRoot.setOnClickListener {
                onClickListener(url)
            }
        }
    }

}