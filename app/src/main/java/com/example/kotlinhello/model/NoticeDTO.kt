package com.example.kotlinhello.model

import com.google.gson.annotations.SerializedName

class NoticeDTO(
    @SerializedName("title") var title: String,
    @SerializedName("content") var content : String
) {
}