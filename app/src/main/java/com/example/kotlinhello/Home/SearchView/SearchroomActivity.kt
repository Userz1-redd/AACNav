package com.example.kotlinhello.Home.SearchView

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinhello.Constants.RESPONSE_STATE
import com.example.kotlinhello.Home.retrofit.RetrofitManager
import com.example.kotlinhello.R
import com.example.kotlinhello.databinding.ActivitySearchroomBinding

class SearchroomActivity : AppCompatActivity() {
    private val TAG = "로그"
    private var searchType : String = "roomnameSearch"
    lateinit var mBinding : ActivitySearchroomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySearchroomBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        //라디오 그룹 가져오기
        mBinding.searchRadioGroup.setOnCheckedChangeListener{_,id ->
            when(id){
                R.id.search_roomname_rd ->{
                    Log.d(TAG, "이름검색 클릭")
                    mBinding.searchTextLayout.hint = "이름검색"
                    this.searchType = "roomnameSearch"
                }
                R.id.search_username_rd ->{
                    Log.d(TAG, "사용자검색 클릭")
                    mBinding.searchTextLayout.hint = "사용자검색"
                    this.searchType = "usernameSearch"
                }
            }

        }
        mBinding.searchBtn.setOnClickListener {
            RetrofitManager.instance.searchPhotos(searchTerm = mBinding.searchText.text.toString(),completion = {
                responseState, responseBody ->
                when(responseState){
                     RESPONSE_STATE.OKAY ->{
                         Log.d(TAG, "API 호출 성공 : ${responseBody}")

                     }
                    RESPONSE_STATE.FAIL ->{
                        Toast.makeText(this,"api 호출 에러입니다",Toast.LENGTH_SHORT).show()
                    }

                }
            })
        }
    }
}