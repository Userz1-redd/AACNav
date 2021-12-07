package com.example.kotlinhello

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.kotlinhello.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var mBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        // 네비게이션들을 담을 호스트
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host) as NavHostFragment

        //컨트롤러
        val navController = navHostFragment.navController

        //바텀 네비게이션뷰와 네비게이션을 묶는다
        NavigationUI.setupWithNavController(mBinding.myBottomNav,navController)
    }

}