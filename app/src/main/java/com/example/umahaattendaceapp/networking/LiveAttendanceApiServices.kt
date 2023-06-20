package com.example.umahaattendaceapp.networking

import com.example.umahaattendaceapp.model.ForgotPasswordResponse
import com.example.umahaattendaceapp.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LiveAttendanceApiServices {

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("auth/login")
    fun loginRequest(@Body body: String): Call<LoginResponse>


    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("auth/password/forgot")
    fun forgotPasswordRequest(@Body body: String): Call<ForgotPasswordResponse>
}