package com.example.kotlinhello.DataClass

import android.util.Log
import com.google.gson.annotations.SerializedName

data class MemberRegisterDTO(
    @SerializedName("name")
    var name : String,
    @SerializedName("email")
    var email :String,
    @SerializedName("password")
    var password : String)
{
    private val TAG = "MyFriend"
    init {
        Log.d(TAG, "MyFriend Construct ")
    }
}