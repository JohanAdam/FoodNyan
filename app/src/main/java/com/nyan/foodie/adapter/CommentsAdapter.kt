package com.nyan.foodie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nyan.foodie.databinding.ListItemCommentBinding
import com.nyan.foodie.binding.model.restaurant.Comment as CommentBinding
import com.nyan.foodie.databinding.ListItemPictureBinding

class CommentsAdapter(private val onClickListener: (CommentBinding) -> Unit): ListAdapter<CommentBinding, CommentsAdapter.CommentsViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<CommentBinding>() {
        override fun areItemsTheSame(oldItem: CommentBinding, newItem: CommentBinding): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CommentBinding, newItem: CommentBinding): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        return CommentsViewHolder(
            ListItemCommentBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        val comment = getItem(position)
        //Bind the data to xml.
        holder.bind(comment, onClickListener)
    }

    class CommentsViewHolder(private var binding: ListItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: CommentBinding, onClickListener: (CommentBinding) -> Unit) {
            binding.comment = comment
            binding.executePendingBindings()

            //Set on click listener and data to sent.
//            binding.cardRoot.setOnClickListener {
//                onClickListener(comment)
//            }
        }
    }

}