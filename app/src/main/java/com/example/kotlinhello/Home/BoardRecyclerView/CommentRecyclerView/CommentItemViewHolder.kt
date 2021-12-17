package com.example.kotlinhello.Home.BoardRecyclerView.CommentRecyclerView

import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinhello.databinding.LayoutCommentItemBinding
import com.example.kotlinhello.model.CommentResponseDTO

class CommentItemViewHolder(private var mBinding : LayoutCommentItemBinding) : RecyclerView.ViewHolder(mBinding.root){

    fun bindWithView(commentItem : CommentResponseDTO){
        mBinding!!.textComment.text = commentItem.content
        mBinding!!.textName.text = commentItem.writerName
    }
}