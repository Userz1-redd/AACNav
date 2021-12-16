package com.example.kotlinhello.Home.BoardRecyclerView

interface ISearchHistoryRecyclerView {
    //검색 아이템 삭제 버튼 클릭
    fun onSearchItemDeleteBtnClicked(position: Int)

    //검색 버튼 클릭
    fun onSearchItemClicked(position: Int)
}