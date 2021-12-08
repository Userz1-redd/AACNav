package com.example.kotlinhello.Login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinhello.databinding.ActivityFindPasswordBinding

class FindPasswordActivity : AppCompatActivity() {
    lateinit var mBinding : ActivityFindPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityFindPasswordBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }
}