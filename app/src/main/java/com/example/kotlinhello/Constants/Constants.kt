package com.example.kotlinhello.Constants

object API{
    const val BASE_URL = "http://3.38.97.106:8080/"
    const val CLIENT_ID : String = "gRQEYwHhWS5L3m8tdgVALFqOidChGIIkUNLY7mkR4PM"
    const val SEARCH_PHOTO : String ="search/photos"
    const val SEARCH_USERS : String ="search/users"
    //유저관련
    const val REGIST_USER : String = "members/register"
    const val LOGIN_USER : String = "members/login"
    const val UPDATE_NAME : String = "members/name"
    const val UPDATE_PASSWD : String = "members/password"
    const val DELETE_USER : String = "members"
    /*유저관련끝*/

    //게시판관련
    const val BASE_BOARD : String = "boards"
    const val KEYWORD_BOARD : String = "boards/title/{string}"
    const val UPDATE_BOARD : String = "boards/{noticeId}"


}
enum class RESPONSE_STATE{
    OKAY,
    CHECK,
    FAIL
}