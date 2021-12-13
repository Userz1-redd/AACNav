package com.example.kotlinhello.Home.BoardRecyclerView

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinhello.databinding.LayoutBoardItemBinding
import com.example.kotlinhello.model.NoticeResponseDTO

class BoardItemViewHolder(private val binding : LayoutBoardItemBinding) :RecyclerView.ViewHolder(binding.root){

    fun bindWithView(BoardItem : NoticeResponseDTO){
        binding.itemDate.text = BoardItem.date
        binding.itemTitle.text = BoardItem.title
        binding.itemViews.text = BoardItem.views.toString()
        binding.itemWriter.text = BoardItem.writerName

        Log.d("TAG", "bindWithView: called")

    }
}