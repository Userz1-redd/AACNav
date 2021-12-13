package com.example.kotlinhello.Login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinhello.Constants.RESPONSE_STATE
import com.example.kotlinhello.Home.MainActivity
import com.example.kotlinhello.databinding.ActivitySigninBinding
import com.example.kotlinhello.model.UserLoginDTO
import com.example.kotlinhello.retrofit.RetrofitManager

class SigninActivity : AppCompatActivity() {
    private val TAG = "Signin"
    lateinit var mBinding :ActivitySigninBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.backBtn.setOnClickListener {
            finish()
        }
        mBinding.loginBtn.setOnClickListener {
            var email = mBinding.textEmail.text.toString()
            var pwd = mBinding.textPw.text.toString()
            var send_data = UserLoginDTO(email,pwd)
            RetrofitManager.instance.loginUser(param = send_data,completion = {
                    reponseState ->
                when(reponseState){
                    RESPONSE_STATE.OKAY ->{
                        Log.d(TAG, "${email} 로그인 성공 : ${reponseState} ")
                        Toast.makeText(this,"Login Success",Toast.LENGTH_LONG).show()
                        var intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    RESPONSE_STATE.CHECK ->{
                        Log.d(TAG, "로그인 파라미터 확인 : ${reponseState} ")
                        Toast.makeText(this,"이름, 이메일 또는 패스워드 형식을 확인해주세요.",Toast.LENGTH_SHORT).show()
                    }
                    RESPONSE_STATE.FAIL -> {
                        Log.d(TAG, "Server Closed")
                        Toast.makeText(this,"서버 문제로 로그인 실패하였습니다.",Toast.LENGTH_SHORT).show()
                    }

                }

            })


            /*
            Toast.makeText(this,"Login Success",Toast.LENGTH_LONG).show()
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

             */
        }
        mBinding.registerText.setOnClickListener{
            var intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
        mBinding.forgetText.setOnClickListener{
            Toast.makeText(this,"현재 개발중입니다.",Toast.LENGTH_SHORT).show()
        }
    }
}