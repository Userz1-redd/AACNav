package com.example.kotlinhello.Constants

import android.content.Context
import com.example.kotlinhello.App
import com.example.kotlinhello.model.SearchBoardData
import com.google.gson.Gson

object SharedPreferenceManager {
    private const val SHARED_SEARCH_HISTORY = "shared_search_history"
    private const val KEY_SEARCH_HISTORY = "key_search_history"
    //검색목록저장
    fun storeBoardSearchHistoryList(boardSearchHistoryList : MutableList<SearchBoardData>){
        //매개변수로 들어온 배열 문자열로 변환
        val boardSearchHistoryListString : String = Gson().toJson(boardSearchHistoryList)

        //쉐어드 가져오기
        val shared = App.instance.getSharedPreferences(SHARED_SEARCH_HISTORY, Context.MODE_PRIVATE)

        //쉐어드 에디터 가져오기
        val editor = shared.edit()
        editor.putString(KEY_SEARCH_HISTORY,boardSearchHistoryListString)
        editor.apply()
    }
    fun getBoardSearchHistoryList() : MutableList<SearchBoardData>{
        //쉐어드 가져오기
        val shared = App.instance.getSharedPreferences(SHARED_SEARCH_HISTORY, Context.MODE_PRIVATE)

        val storedBoardSearchHistoryListString = shared.getString(KEY_SEARCH_HISTORY,"")!!
        var storedBoardSearchHistoryList = ArrayList<SearchBoardData>()
        if(storedBoardSearchHistoryListString.isNotEmpty()){

            // 저장된 문자열을 객체배열로 변환
            storedBoardSearchHistoryList = Gson().fromJson(storedBoardSearchHistoryListString,Array<SearchBoardData>::class.java)
                .toMutableList() as ArrayList<SearchBoardData>
        }

        return storedBoardSearchHistoryList
    }
}