package com.example.kotlinhello.Login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinhello.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
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
    }
}