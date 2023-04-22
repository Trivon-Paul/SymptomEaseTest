package com.example.symptomease

import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {
    @Headers("Authorization: Bearer pault@my.ccsu.edu:MQfXWJz6BscQCZNerSPdnA==")
    @POST("login")
    fun login() : Call<Login>

}