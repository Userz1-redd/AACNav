package com.example.kotlinhello.Login

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
    }
}