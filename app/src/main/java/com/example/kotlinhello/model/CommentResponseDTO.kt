package com.example.kotlinhello.model

import com.google.gson.annotations.SerializedName

class CommentResponseDTO (
    @SerializedName("content") var content : String,
    @SerializedName("writerName") var writerName :String
        ){
}