package com.example.kotlinhello.Home.BoardRecyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinhello.databinding.LayoutBoardItemBinding
import com.example.kotlinhello.model.NoticeResponseDTO

class BoardRecyclerViewAdapter : RecyclerView.Adapter<BoardItemViewHolder>() {
    private var boardList = ArrayList<NoticeResponseDTO>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardItemViewHolder {
        Log.d("TAG", "onCreateViewHolder:called ")
        val binding = LayoutBoardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BoardItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BoardItemViewHolder, position: Int) {
        Log.d("TAG", "onBindViewHolder:called ")
        holder.bindWithView(this.boardList[position])
    }

    override fun getItemCount(): Int {
        return this.boardList.size
    }
    fun submitList(boardList : ArrayList<NoticeResponseDTO>){
         this.boardList = boardList
    }

}