package com.example.kotlinhello.Login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinhello.MainActivity
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
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}