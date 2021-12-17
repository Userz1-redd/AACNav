package com.example.kotlinhello.Home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinhello.Constants.RESPONSE_STATE
import com.example.kotlinhello.databinding.ActivityBoardContentBinding
import com.example.kotlinhello.model.CommentDTO
import com.example.kotlinhello.model.NoticeDetailDTO
import com.example.kotlinhello.retrofit.RetrofitManager

class BoardContentActivity : AppCompatActivity(), View.OnClickListener{
    lateinit var mBinding : ActivityBoardContentBinding
    lateinit var detailBoard : NoticeDetailDTO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityBoardContentBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        var bundle = intent.getBundleExtra("board_bundle")
        detailBoard = bundle!!.getSerializable("board_data") as NoticeDetailDTO
        settingUI(detailBoard)
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

    override fun onClick(v: View?) {
        when(v){
            mBinding!!.submitBtn ->{
                    Log.d("TAG", "onClick: 등록버튼클릭")
                postComment(CommentDTO(this.detailBoard.id,mBinding.textComment.text.toString()))
            }
        }
    }
}