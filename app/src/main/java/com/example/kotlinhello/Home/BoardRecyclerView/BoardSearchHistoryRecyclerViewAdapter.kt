package com.example.kotlinhello.Home.BoardRecyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinhello.databinding.LayoutSearchBoardItemBinding
import com.example.kotlinhello.model.SearchBoardData
class BoardSearchHistoryRecyclerViewAdapter(searchIntf : ISearchHistoryRecyclerView): RecyclerView.Adapter<BoardSearchItemViewHolder>() {
    private var boardSearchList = ArrayList<SearchBoardData>()
    private var iSearchIntf : ISearchHistoryRecyclerView? = null
    init{
        this.iSearchIntf = searchIntf
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardSearchItemViewHolder {
        Log.d("TAG", "onCreateViewHolder:called ")
        val binding = LayoutSearchBoardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BoardSearchItemViewHolder(binding,this.iSearchIntf!!)
    }

    override fun onBindViewHolder(holder: BoardSearchItemViewHolder, position: Int) {
        Log.d("TAG", "onBindViewHolder:called ")
        holder.bindWithView(this.boardSearchList[position])
    }

    override fun getItemCount(): Int {
        return this.boardSearchList.size
    }
    fun submitList(boardSearchList : ArrayList<SearchBoardData>){
        this.boardSearchList = boardSearchList
    }

}