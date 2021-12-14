package com.example.kotlinhello.retrofit

import com.example.kotlinhello.Constants.API
import com.example.kotlinhello.model.UserDTO
import com.example.kotlinhello.model.UserLoginDTO
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
    fun registUser(@Body param : UserDTO): Call<JsonElement>

    @Headers("accept: application/json",
        "content-type: application/json")
    @POST(API.LOGIN_USER)
    fun loginUser(@Body param : UserLoginDTO): Call<JsonElement>

    @Headers("accept: application/json",
        "content-type: application/json")
    @PATCH(API.UPDATE_NAME)
    fun updateUserName(@Body param : String): Call<JsonElement>
    @Headers("accept: application/json",
        "content-type: application/json")
    @PATCH(API.UPDATE_PASSWD)
    fun updatePasswd(@Body param : String): Call<JsonElement>
    @Headers("accept: application/json",
        "content-type: application/json")
    @DELETE(API.DELETE_USER)
    fun deleteUser()
    /*유저관련 API*/


    //게시판관련API
    @GET(API.BASE_BOARD)
    fun getBoardList() : Call<JsonElement>
    @GET(API.KEYWORD_BOARD)
    fun getKeywordBoardList(@Path("string") string : String) : Call<JsonElement>



}

