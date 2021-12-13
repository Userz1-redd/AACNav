package com.example.kotlinhello.Home


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinhello.App
import com.example.kotlinhello.Constants.RESPONSE_STATE
import com.example.kotlinhello.Home.BoardRecyclerView.BoardRecyclerViewAdapter
import com.example.kotlinhello.databinding.FragmentBoardBinding
import com.example.kotlinhello.model.NoticeResponseDTO
import com.example.kotlinhello.retrofit.RetrofitManager


class BoardFragment : Fragment() {
    private val TAG = "TAG"
    private var mBinding : FragmentBoardBinding? = null
    //데이터
    private var boardList  = ArrayList<NoticeResponseDTO>()
    private lateinit var boardRecyclerViewAdapter : BoardRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBoardBinding.inflate(inflater,container,false)
        mBinding = binding

        //게시판 API
        RetrofitManager.instance.getBoardList(completion = {
                   reponseState,responseBody ->
                    when(reponseState){
                   RESPONSE_STATE.OKAY ->{
                       Log.d(TAG, "게시판 로딩 성공 : ${reponseState} ")
                       boardList = responseBody!!
                       mBinding!!.boardRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                       this.boardRecyclerViewAdapter = BoardRecyclerViewAdapter()
                       this.boardRecyclerViewAdapter.submitList(boardList)
                       mBinding!!.boardRecyclerView.adapter = this.boardRecyclerViewAdapter
                   }
                   RESPONSE_STATE.CHECK ->{
                       Log.d(TAG, "게시판 로딩 실패: ${reponseState} ")
                       Toast.makeText(App.instance,"게시판 로딩 실패",Toast.LENGTH_SHORT).show()
                   }
                   RESPONSE_STATE.FAIL -> {
                       Log.d(TAG, "Server Closed")
                       Toast.makeText(App.instance,"Server closed",Toast.LENGTH_SHORT).show()
                   }

               }

           })


    return mBinding!!.root
    }

override fun onDestroyView(){
    mBinding = null
    super.onDestroyView()
    }
}