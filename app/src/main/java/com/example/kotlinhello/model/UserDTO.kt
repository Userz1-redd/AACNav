package com.example.kotlinhello.model

import android.util.Log
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserDTO(
    @SerializedName("name")
    var name : String,
    @SerializedName("email")
    var email :String,
    @SerializedName("password")
    var password : String) : Serializable
{
    private val TAG = "TAG"
    init {
        Log.d(TAG, "User Construct ")
    }
}