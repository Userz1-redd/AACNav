package com.example.kotlinhello.Home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinhello.Constants.RESPONSE_STATE
import com.example.kotlinhello.databinding.ActivityBoardPostBinding
import com.example.kotlinhello.model.NoticeDTO
import com.example.kotlinhello.retrofit.RetrofitManager

class BoardPostActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var mBinding : ActivityBoardPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityBoardPostBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.backBtn.setOnClickListener(this)
        mBinding.submitBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            mBinding.backBtn -> finish()
            mBinding.submitBtn ->{
                var title = mBinding.textTitle.text.toString()
                var content = mBinding.textContent.text.toString()
                Log.d("TAG", "onClick: ${content}")
                var send_data = NoticeDTO(title,content)
                RetrofitManager.instance.postBoard(param = send_data,completion = {
                        reponseState ->
                    when(reponseState){
                        RESPONSE_STATE.OKAY ->{
                            Toast.makeText(this,"게시글을 성공적으로 작성하였습니다.", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                        RESPONSE_STATE.CHECK ->{
                            Toast.makeText(this,"게시글은 최소 4자이상 작성해주세요", Toast.LENGTH_SHORT).show()
                        }
                        RESPONSE_STATE.FAIL -> {
                            Toast.makeText(this,"게시글 작성에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                        }

                    }

                })

            }

        }
    }
}