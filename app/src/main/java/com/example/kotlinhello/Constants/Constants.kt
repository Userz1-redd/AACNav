package com.example.kotlinhello.Constants

object API{
    const val BASE_URL = "http://13.124.245.229:8080/"
    const val CLIENT_ID : String = "gRQEYwHhWS5L3m8tdgVALFqOidChGIIkUNLY7mkR4PM"
    const val SEARCH_PHOTO : String ="search/photos"
    const val SEARCH_USERS : String ="search/users"
    const val REGIST_USER : String = "members/register"
    const val LOGIN_USER : String = "members/login"
    const val UPDATE_NAME : String = "members/name"
    const val UPDATE_PASSWD : String = "members/password"
    const val DELETE_USER : String = "members"

}
enum class RESPONSE_STATE{
    OKAY,
    CHECK,
    FAIL
}