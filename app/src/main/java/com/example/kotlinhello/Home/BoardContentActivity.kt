package com.example.kotlinhello.Home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinhello.App
import com.example.kotlinhello.Constants.RESPONSE_STATE
import com.example.kotlinhello.Home.BoardRecyclerView.CommentRecyclerView.CommentRecyclerViewAdapter
import com.example.kotlinhello.databinding.ActivityBoardContentBinding
import com.example.kotlinhello.model.CommentDTO
import com.example.kotlinhello.model.CommentResponseDTO
import com.example.kotlinhello.model.NoticeDetailDTO
import com.example.kotlinhello.retrofit.RetrofitManager

class BoardContentActivity : AppCompatActivity(), View.OnClickListener{
    lateinit var mBinding : ActivityBoardContentBinding
    lateinit var detailBoard : NoticeDetailDTO
    lateinit var commentList : ArrayList<CommentResponseDTO>
    lateinit var commentRecyclerViewAdapter: CommentRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityBoardContentBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        var bundle = intent.getBundleExtra("board_bundle")
        detailBoard = bundle!!.getSerializable("board_data") as NoticeDetailDTO
        settingUI(detailBoard)
        getCommentList(detailBoard.id)
        mBinding.submitBtn.setOnClickListener(this)
    }
    //받은 intent로 게시글구성
    fun settingUI(detailBoard : NoticeDetailDTO){
        mBinding!!.textName.text = detailBoard.writerName
        mBinding!!.textDate.text = detailBoard.date
        mBinding!!.textContent.text = detailBoard.content
        mBinding!!.textTitle.text = detailBoard.title
        mBinding!!.textViews.text = detailBoard.views.toString()
    }
    fun postComment(commentDTO: CommentDTO){
        RetrofitManager.instance.postComment(param = commentDTO,completion = {
                reponseState ->
            when(reponseState){
                RESPONSE_STATE.OKAY ->{
                    Log.d("TAG", "postComment: success")
                    Toast.makeText(this,"댓글을 성공적으로 작성하였습니다.", Toast.LENGTH_SHORT).show()
                }
                RESPONSE_STATE.CHECK ->{
                    Log.d("TAG", "postComment: check")
                    Toast.makeText(this,"댓글은 최소 2자이상 작성해주세요", Toast.LENGTH_SHORT).show()
                }
                RESPONSE_STATE.FAIL -> {
                    Log.d("TAG", "postComment: fail")
                    Toast.makeText(this,"댓글 작성에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }

            }

        })
    }
    fun getCommentList(id : Long){
        RetrofitManager.instance.getCommentList(id,completion = {
                    reponseState,responseBody ->
                when(reponseState){
                    RESPONSE_STATE.OKAY ->{
                        Log.d("TAG", "댓글 로딩 성공 : ${reponseState} ")
                        this.commentList = responseBody!!
                        mBinding!!.commentRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                        this.commentRecyclerViewAdapter = CommentRecyclerViewAdapter()
                        this.commentRecyclerViewAdapter.submitList(commentList)
                        mBinding!!.commentRecyclerView.adapter = this.commentRecyclerViewAdapter
                    }
                    RESPONSE_STATE.CHECK ->{
                        Log.d("TAG", "댓글 로딩 실패: ${reponseState} ")
                        Toast.makeText(App.instance,"댓글 로딩 실패",Toast.LENGTH_SHORT).show()
                    }
                    RESPONSE_STATE.FAIL -> {
                        Log.d("TAG", "Server Closed")
                        Toast.makeText(App.instance,"Server closed",Toast.LENGTH_SHORT).show()
                    }

                }

            })

        }

    override fun onClick(v: View?) {
        when(v){
            mBinding!!.submitBtn ->{
                    Log.d("TAG", "onClick: 등록버튼클릭")
                postComment(CommentDTO(this.detailBoard.id,mBinding.textComment.text.toString()))
            }
        }
    }
}