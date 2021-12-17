package com.example.kotlinhello.retrofit

import android.util.Log
import com.example.kotlinhello.Constants.API
import com.example.kotlinhello.Constants.RESPONSE_STATE
import com.example.kotlinhello.model.*
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
    fun registUser(param : UserDTO, completion: (RESPONSE_STATE) -> Unit){
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
                    else -> {completion(RESPONSE_STATE.CHECK)}
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "onFailure: called")
                completion(RESPONSE_STATE.FAIL)
            }
        })

    }
    //로그인 api호출
    fun loginUser(param : UserLoginDTO, completion: (RESPONSE_STATE) -> Unit){
        val term = param.let{
            it
        }
        val call = iRetrofit?.loginUser(param=term).let{
            it
        }?: return
        call.enqueue(object : retrofit2.Callback<JsonElement>{
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "onResponse: called /t:${response.raw()}")
                when(response.code()){
                    200->{completion(RESPONSE_STATE.OKAY)}
                    else -> {completion(RESPONSE_STATE.CHECK)}
                }
            }
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "onFailure: called")
                completion(RESPONSE_STATE.FAIL)
            }
        })

    }

    //게시판 가져오기 api 호출
    fun getBoardList(completion: (RESPONSE_STATE, ArrayList<NoticeResponseDTO>?) -> Unit){
        val call = iRetrofit?.getBoardList().let{
            it
        }?: return
        call.enqueue(object : retrofit2.Callback<JsonElement>{
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "onResponse: called /t:${response.raw()}")
                Log.d(TAG, "onResponse: ${response.body()}")
                when(response.code()){
                    200->{
                        response.body()?.let{
                            val body = it.asJsonArray
                            Log.d(TAG, "body: ${body}")
                            val list = ArrayList<NoticeResponseDTO>()
                            body.forEach{
                                item ->
                                val resultItemObject = item.asJsonObject
                                val id = resultItemObject.get("id").asLong
                                val title = resultItemObject.get("title").asString
                                val writerName = resultItemObject.get("writerName").asString
                                val views = resultItemObject.get("views").asLong
                                val date = resultItemObject.get("date").asString
                                val obj = NoticeResponseDTO(id,title,writerName,views, date)
                                list.add(obj)
                            }
                            completion(RESPONSE_STATE.OKAY,list)}
                        }
                    else -> {completion(RESPONSE_STATE.CHECK,null)}
                }
            }
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "onFailure: called")
                completion(RESPONSE_STATE.FAIL,null)
            }
        })

    }
    fun getKeywordBoardList(keyword : String,completion: (RESPONSE_STATE, ArrayList<NoticeResponseDTO>?) -> Unit){
        val call = iRetrofit?.getKeywordBoardList(keyword).let{
            it
        }?: return
        call.enqueue(object : retrofit2.Callback<JsonElement>{
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "onResponse: called /t:${response.raw()}")
                Log.d(TAG, "onResponse: ${response.body()}")
                when(response.code()){
                    200->{
                        response.body()?.let{
                            val body = it.asJsonArray
                            Log.d(TAG, "body: ${body}")
                            val list = ArrayList<NoticeResponseDTO>()
                            body.forEach{
                                    item ->
                                val resultItemObject = item.asJsonObject
                                val id = resultItemObject.get("id").asLong
                                val title = resultItemObject.get("title").asString
                                val writerName = resultItemObject.get("writerName").asString
                                val views = resultItemObject.get("views").asLong
                                val date = resultItemObject.get("date").asString
                                val obj = NoticeResponseDTO(id,title,writerName,views, date)
                                list.add(obj)
                            }
                            completion(RESPONSE_STATE.OKAY,list)}
                    }
                    else -> {completion(RESPONSE_STATE.CHECK,null)}
                }
            }
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "onFailure: called")
                completion(RESPONSE_STATE.FAIL,null)
            }
        })

    }

    //게시글 세부정보 가져오기
        fun getDetailBoard(param : Long,completion: (RESPONSE_STATE, NoticeDetailDTO?) -> Unit){
        val call = iRetrofit?.getDetailBoard(param).let{
            it
        }?: return
        call.enqueue(object : retrofit2.Callback<JsonElement>{
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "onResponse: called /t:${response.raw()}")
                Log.d(TAG, "onResponse: ${response.body()}")
                when(response.code()){
                    200->{
                        lateinit var obj : NoticeDetailDTO
                        response.body()?.let{
                            val body = it.asJsonObject
                            Log.d(TAG, "body: ${body}")
                            val id = body.get("id").asLong
                            val title = body.get("title").asString
                            val writerName = body.get("writerName").asString
                            val views = body.get("views").asLong
                            val date = body.get("date").asString
                            val content = body.get("content").asString
                            obj = NoticeDetailDTO(id,title,writerName,views, date,content)
                        }
                        completion(RESPONSE_STATE.OKAY,obj)
                    }
                    else -> {completion(RESPONSE_STATE.CHECK,null)}
                }
            }
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "onFailure: called")
                completion(RESPONSE_STATE.FAIL,null)
            }
        })

    }





    //게시글 작성
    fun postBoard(param : NoticeDTO, completion: (RESPONSE_STATE) -> Unit){
        val term = param.let{
            it
        }
        val call = iRetrofit?.postBoard(param = term).let{
            it
        }?: return
        call.enqueue(object : retrofit2.Callback<JsonElement>{
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "onResponse: called /t:${response.raw()}")
                when(response.code()){
                    200->{completion(RESPONSE_STATE.OKAY)}
                    else -> {completion(RESPONSE_STATE.CHECK)}
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "onFailure: called")
                completion(RESPONSE_STATE.FAIL)
            }
        })

    }
    fun postComment(param : CommentDTO, completion: (RESPONSE_STATE) -> Unit){
        val term = param.let{
            it
        }
        val call = iRetrofit?.postComment(param = term).let{
            it
        }?: return
        call.enqueue(object : retrofit2.Callback<JsonElement>{
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "onResponse: called /t:${response.raw()}")
                when(response.code()){
                    200->{completion(RESPONSE_STATE.OKAY)}
                    else -> {completion(RESPONSE_STATE.CHECK)}
                }
            }
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "onFailure: called")
                completion(RESPONSE_STATE.FAIL)
            }
        })

    }

    fun getCommentList(option : String, id : Long,completion: (RESPONSE_STATE, ArrayList<NoticeResponseDTO>?) -> Unit){
        val call = iRetrofit?.getCommentList(option,id).let{
            it
        }?: return
        call.enqueue(object : retrofit2.Callback<JsonElement>{
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "onResponse: called /t:${response.raw()}")
                Log.d(TAG, "onResponse: ${response.body()}")
                when(response.code()){
                    200->{
                        response.body()?.let{
                            val body = it.asJsonArray
                            Log.d(TAG, "body: ${body}")
                            val list = ArrayList<NoticeResponseDTO>()
                            body.forEach{
                                    item ->
                                val resultItemObject = item.asJsonObject
                                val id = resultItemObject.get("id").asLong
                                val title = resultItemObject.get("title").asString
                                val writerName = resultItemObject.get("writerName").asString
                                val views = resultItemObject.get("views").asLong
                                val date = resultItemObject.get("date").asString
                                val obj = NoticeResponseDTO(id,title,writerName,views, date)
                                list.add(obj)
                            }
                            completion(RESPONSE_STATE.OKAY,list)}
                    }
                    else -> {completion(RESPONSE_STATE.CHECK,null)}
                }
            }
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "onFailure: called")
                completion(RESPONSE_STATE.FAIL,null)
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