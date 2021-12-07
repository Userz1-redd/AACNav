package com.example.kotlinhello.Login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinhello.R

class LoadingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        },2000)
    }
}