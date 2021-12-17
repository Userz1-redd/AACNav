package com.example.kotlinhello.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class NoticeDetailDTO(
    @SerializedName("id") var id : Long,
    @SerializedName("title") var title : String,
    @SerializedName("writerName") var writerName : String,
    @SerializedName("views") var views : Long,
    @SerializedName("date") var date : String,
    @SerializedName("content") var content : String
) : Serializable{
}