package com.example.kotlinhello.DataClass

import android.util.Log

data class MyFriend(var name : String,var phonenumber :String) {
    private val TAG = "MyFriend"
    init {
        Log.d(TAG, "MyFriend Construct ")
    }
}