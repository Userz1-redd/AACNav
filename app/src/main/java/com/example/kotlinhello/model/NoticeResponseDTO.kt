package com.example.kotlinhello.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NoticeResponseDTO(
    @SerializedName("title") var title : String,
    @SerializedName("writerName") var writerName : String,
    @SerializedName("views") var views : Long,
    @SerializedName("date") var date : String
) : Serializable{
}