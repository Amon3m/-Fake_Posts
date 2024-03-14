package com.firrnas.fakeposts.presentation.view.posts.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.firrnas.fakeposts.databinding.PostItemBinding
import com.firrnas.fakeposts.presentation.model.PostUIModel

class PostsAdapter (private val onClick: (String) -> Unit) :
    ListAdapter<PostUIModel, PostsAdapter.PostViewHolder>(RecyclerDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater: LayoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = PostItemBinding.inflate(inflater, parent, false)
        return PostViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.onBind(currentItem)
    }

    inner class PostViewHolder(private val binding: PostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(currentItem: PostUIModel) {
            binding.tvUserId.text = currentItem.userId.toString()
            binding.tvTitle.text = currentItem.title.toString()
            binding.tvBody.text = currentItem.body
            binding.cvAlbum.setOnClickListener {
                onClick(currentItem.id.toString())
            }
        }
    }
}

class RecyclerDiffUtil : DiffUtil.ItemCallback<PostUIModel>() {
    override fun areItemsTheSame(oldItem: PostUIModel, newItem: PostUIModel): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: PostUIModel, newItem: PostUIModel): Boolean {
        return oldItem == newItem
    }
}