package com.nyan.foodie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nyan.foodie.databinding.ListItemFilterBinding
import com.nyan.foodie.databinding.ListItemPictureBinding

class TagsAdapter(private val onClickListener: (String) -> Unit): ListAdapter<String, TagsAdapter.TagsViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem === newItem
        }

        //This is no need, actually.
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder {
        return TagsViewHolder(
            ListItemFilterBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        val tag = getItem(position)
        //Bind the data to xml.
        holder.bind(tag, onClickListener)
    }

    class TagsViewHolder(private var binding: ListItemFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tag: String, onClickListener: (String) -> Unit) {
            binding.tvFilterType.text = tag

            //Set on click listener and data to sent.
            binding.cardRoot.setOnClickListener {
                onClickListener(tag)
            }
        }
    }

}