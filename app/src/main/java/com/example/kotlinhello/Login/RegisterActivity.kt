package com.example.kotlinhello.Login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinhello.Constants.RESPONSE_STATE
import com.example.kotlinhello.DataClass.MemberRegisterDTO
import com.example.kotlinhello.Home.retrofit.RetrofitManager
import com.example.kotlinhello.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private val TAG = "RegisterActivity"
    lateinit var mBinding : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.backBtn.setOnClickListener {
            finish()
        }
        mBinding.loginText.setOnClickListener {
            var intent = Intent(this,SigninActivity::class.java)
            startActivity(intent)
        }
        mBinding.signupBtn.setOnClickListener{
            val input_name = mBinding.textName.text.toString()
            val input_email = mBinding.textEmail.text.toString()
            val input_pwd = mBinding.textPw.text.toString()
            val send_data = MemberRegisterDTO(input_name,input_email,input_pwd)
            Log.d("태그","${send_data.name}")

            RetrofitManager.instance.registUser(param = send_data,completion = {
                reponseState ->
                when(reponseState){
                    RESPONSE_STATE.OKAY ->{
                        Log.d(TAG, "onCreate: 회원가입 성공 : ${reponseState} ")
                        Toast.makeText(this,"${input_name}님 회원가입 성공",Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    RESPONSE_STATE.FAIL -> {
                        Log.d(TAG, "onCreate: 회원가입 실패")
                        Toast.makeText(this,"회원가입에 실패하였습니다.",Toast.LENGTH_SHORT).show()
                    }

                }

            })
        }

       /* mBinding.searchBtn.setOnClickListener {
            RetrofitManager.instance.searchPhotos(searchTerm = mBinding.searchText.text.toString(),completion = {
                    responseState, responseBody ->
                when(responseState){
                    RESPONSE_STATE.OKAY ->{
                        Log.d(TAG, "API 호출 성공 : ${responseBody}")

                    }
                    RESPONSE_STATE.FAIL ->{
                        Toast.makeText(this,"api 호출 에러입니다", Toast.LENGTH_SHORT).show()
                    }

                }
            })
        }*/
    }
}