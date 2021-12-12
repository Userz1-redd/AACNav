package com.example.kotlinhello.Constants

fun String?.isJsonObject():Boolean {
    return this?.startsWith("{")==true &&this.endsWith("}")
}

fun String?.isJsonArray() : Boolean =this?.startsWith("[")==true &&this.endsWith("]")