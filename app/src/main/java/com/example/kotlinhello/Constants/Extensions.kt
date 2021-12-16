package com.example.kotlinhello.Constants

import java.text.SimpleDateFormat
import java.util.*

fun String?.isJsonObject():Boolean {
    return this?.startsWith("{")==true &&this.endsWith("}")
}

fun String?.isJsonArray() : Boolean =this?.startsWith("[")==true &&this.endsWith("]")

fun Date.toSimpleString() : String{
    val format = SimpleDateFormat("HH:mm:ss")
    return format.format(this)

}