package com.example.kotlinhello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinhello.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var mBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }
}