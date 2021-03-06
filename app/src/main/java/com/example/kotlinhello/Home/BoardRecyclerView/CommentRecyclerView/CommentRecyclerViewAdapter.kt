package com.example.kotlinhello.Home.BoardRecyclerView.CommentRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinhello.databinding.LayoutCommentItemBinding
import com.example.kotlinhello.model.CommentResponseDTO

class CommentRecyclerViewAdapter : RecyclerView.Adapter<CommentItemViewHolder>() {
    private var commentList = ArrayList<CommentResponseDTO>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentItemViewHolder {
        val binding = LayoutCommentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentItemViewHolder, position: Int) {
       holder.bindWithView(commentList[position])
    }

    override fun getItemCount(): Int {
        return commentList.size
    }
    fun submitList(commentList : ArrayList<CommentResponseDTO>){
        this.commentList = commentList
    }
}