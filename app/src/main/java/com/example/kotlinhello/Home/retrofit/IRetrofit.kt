package com.example.kotlinhello.Home.retrofit

import com.example.kotlinhello.Constants.API
import com.example.kotlinhello.DataClass.MemberRegisterDTO
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.*

interface IRetrofit {
    @GET(API.SEARCH_PHOTO)
    fun searchPhotos(@Query("query") searchTerm: String): Call<JsonElement>

    @GET(API.SEARCH_USERS)
    fun searchUsers(@Query("query") searchTerm: String): Call<JsonElement>

    @Headers("accept: application/json",
        "content-type: application/json")
    @POST(API.REGIST_USER)
    fun registUser(@Body param : MemberRegisterDTO): Call<JsonElement>
}
