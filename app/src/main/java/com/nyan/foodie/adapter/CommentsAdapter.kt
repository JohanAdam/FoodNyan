package com.nyan.foodie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.nyan.foodie.R
import com.nyan.foodie.databinding.ListItemCommentBinding
import com.nyan.foodie.databinding.ListItemCommentLoadMoreBinding
import com.nyan.foodie.binding.model.restaurant.Comment as CommentBinding


class CommentsAdapter(private val onClickListener: (CommentBinding?, Boolean) -> Unit): ListAdapter<CommentBinding, CommentsAdapter.CommentsViewHolder>(
    DiffCallback
) {

    companion object DiffCallback : DiffUtil.ItemCallback<CommentBinding>() {
        override fun areItemsTheSame(oldItem: CommentBinding, newItem: CommentBinding): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CommentBinding, newItem: CommentBinding): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        var itemV: ViewBinding

        if (viewType == R.layout.list_item_comment) {
            itemV = ListItemCommentBinding.inflate(LayoutInflater.from(parent.context))
        } else {
            itemV = ListItemCommentLoadMoreBinding.inflate(LayoutInflater.from(parent.context))
        }

        return CommentsViewHolder(itemV)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        //Bind the data to xml.
        if (position == currentList.size) {
            holder.bindFooter(onClickListener)
        } else {
            val comment = getItem(position)
            holder.bind(comment, onClickListener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == currentList.size) R.layout.list_item_comment_load_more else R.layout.list_item_comment
    }

    class CommentsViewHolder(private var binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: CommentBinding, onClickListener: (CommentBinding?, Boolean) -> Unit) {
            val _binding = (binding as ListItemCommentBinding)
            _binding.comment = comment
            _binding.executePendingBindings()
            //Set on click listener and data to sent.
            _binding.cardRoot.setOnClickListener {
                onClickListener(comment, false)
            }
        }

        fun bindFooter(onClickListener: (CommentBinding?, Boolean) -> Unit) {
            val _binding = (binding as ListItemCommentLoadMoreBinding)
            _binding.btnLoadMore.setOnClickListener {
                onClickListener(null, true)
            }
        }
    }

    override fun getItemCount(): Int {
        return currentList.size + 1
    }


}