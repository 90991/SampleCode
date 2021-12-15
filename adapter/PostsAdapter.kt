package com.example.sampe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sampe.R
import com.example.sampe.databinding.ItemPostsBinding
import com.example.sampe.models.PostsResponseItem

class PostsAdapter(private var list: List<PostsResponseItem>) :
    RecyclerView.Adapter<PostsAdapter.Holder>() {

    class Holder(itemView: ItemPostsBinding) : RecyclerView.ViewHolder(itemView.root) {
        val binding = itemView
        fun bind(postsResponseItem: PostsResponseItem) {
            binding.model = postsResponseItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_posts,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size ?: 0
    }

    fun setData(list: List<PostsResponseItem>) {
        this.list = list
        notifyDataSetChanged()
    }
}