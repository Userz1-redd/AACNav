package com.example.kotlinhello.model

import android.util.Log
import com.google.gson.annotations.SerializedName

data class UserLoginDTO(
    @SerializedName("email") var email : String,
    @SerializedName("password") var password : String
) {
    private val TAG = "TAG"
    init {
        Log.d(TAG, "User Construct ")
    }
}