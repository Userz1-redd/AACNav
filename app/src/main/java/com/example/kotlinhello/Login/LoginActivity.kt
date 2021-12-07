package com.example.kotlinhello.Login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinhello.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var mBinding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.signupBtn.setOnClickListener {
            onClickSignUp()
        }
        mBinding.loginBtn.setOnClickListener{
            onClickLogin()
        }

    }
    fun onClickLogin(){
        var intent = Intent(this,SigninActivity::class.java)
        startActivity(intent)
    }
    fun onClickSignUp(){
        var intent = Intent(this,RegisterActivity::class.java)
        startActivityForResult(intent,100)
    }
}