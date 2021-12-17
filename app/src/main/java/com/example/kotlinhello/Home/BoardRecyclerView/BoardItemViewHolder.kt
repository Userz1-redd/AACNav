package com.example.kotlinhello.Home.BoardRecyclerView

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinhello.databinding.LayoutBoardItemBinding
import com.example.kotlinhello.model.NoticeResponseDTO

class BoardItemViewHolder(private val binding : LayoutBoardItemBinding, boardItemInterface : IBoardRecyclerView) :RecyclerView.ViewHolder(binding.root), View.OnClickListener{
    lateinit var boardItemInterface : IBoardRecyclerView
    init{
        this.boardItemInterface = boardItemInterface
        binding.boardItemView.setOnClickListener(this)
    }

    fun bindWithView(BoardItem : NoticeResponseDTO){
        binding.itemDate.text = BoardItem.date
        binding.itemTitle.text = BoardItem.title
        binding.itemViews.text = BoardItem.views.toString()
        binding.itemWriter.text = BoardItem.writerName

        Log.d("TAG", "bindWithView: called")

    }

    override fun onClick(v: View?) {
       when(v){
           binding.boardItemView -> {
               this.boardItemInterface.onClickBoard(adapterPosition)
           }
       }
    }
}