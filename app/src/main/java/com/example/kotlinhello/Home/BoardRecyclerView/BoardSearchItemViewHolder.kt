package com.example.kotlinhello.Home.BoardRecyclerView

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinhello.databinding.LayoutSearchBoardItemBinding
import com.example.kotlinhello.model.SearchBoardData


class BoardSearchItemViewHolder(private val binding : LayoutSearchBoardItemBinding, searchIntf : ISearchHistoryRecyclerView) : RecyclerView.ViewHolder(binding.root),
    View.OnClickListener{
    private lateinit var mySearchIntf : ISearchHistoryRecyclerView
    init{
        binding.deleteSearchBtn.setOnClickListener(this)
        binding.constraintSearchBoardItem.setOnClickListener(this)
        this.mySearchIntf = searchIntf
    }


    fun bindWithView(BoardItem : SearchBoardData){
       binding.whenSearchedText.text = BoardItem.timestamp
        binding.searchTermText.text = BoardItem.term

    }

    override fun onClick(view: View?) {
        when(view){
            binding.deleteSearchBtn ->{
                Log.d("TAG", "onClick: 검색삭제버튼 클릭")
                this.mySearchIntf.onSearchItemDeleteBtnClicked(adapterPosition)
            }
            binding.constraintSearchBoardItem ->{
                this.mySearchIntf.onSearchItemClicked(adapterPosition)
            }
            else ->{

            }

        }
    }
}