package com.example.kotlinhello.model

import com.google.gson.annotations.SerializedName

class CommentDTO(
    @SerializedName("boardId") var boardId : Long,
    @SerializedName("content") var content : String
) {
}