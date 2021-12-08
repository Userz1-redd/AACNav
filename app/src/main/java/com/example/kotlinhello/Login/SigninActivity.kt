package com.example.kotlinhello.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinhello.Home.MainActivity
import com.example.kotlinhello.databinding.ActivitySigninBinding

class SigninActivity : AppCompatActivity() {
    lateinit var mBinding :ActivitySigninBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.backBtn.setOnClickListener {
            finish()
        }
        mBinding.loginBtn.setOnClickListener {
            Toast.makeText(this,"Login Success",Toast.LENGTH_LONG).show()
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
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