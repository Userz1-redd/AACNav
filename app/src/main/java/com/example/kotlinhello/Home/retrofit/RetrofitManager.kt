package com.example.kotlinhello.Home.retrofit

import android.util.Log
import com.example.kotlinhello.Constants.API
import com.example.kotlinhello.Constants.RESPONSE_STATE
import com.example.kotlinhello.DataClass.MemberRegisterDTO
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response

class RetrofitManager {

    companion object{
        val instance = RetrofitManager()
        private const val TAG = "RetrofitManager"
    }
    //레트로핏 인터페이스 가져오기
    private val iRetrofit : IRetrofit? = RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)

    //회원가입 api호출
    fun registUser(param : MemberRegisterDTO, completion: (RESPONSE_STATE) -> Unit){
        val term = param.let{
            it
        }
        val call = iRetrofit?.registUser(param = term).let{
            it
        }?: return
        call.enqueue(object : retrofit2.Callback<JsonElement>{
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "onResponse: called /t:${response.raw()}")
                when(response.code()){
                    200->{completion(RESPONSE_STATE.OKAY)}

                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "onFailure: called")
                completion(RESPONSE_STATE.FAIL)
            }
        })

    }

    //사진검색 api 호출
    fun searchPhotos(searchTerm: String?, completion: (RESPONSE_STATE,String) -> Unit){
        val term = searchTerm.let{
            it
        }?:""
        val call = iRetrofit?.searchPhotos(searchTerm = term).let{
            it
        }?: return
        call.enqueue(object : retrofit2.Callback<JsonElement>{
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "onResponse: called /t:${response.raw()}")
                when(response.code()){
                    200->{completion(RESPONSE_STATE.OKAY,response.body().toString())}
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "onFailure: called")
                completion(RESPONSE_STATE.FAIL,t.toString())
            }
        })

    }

}