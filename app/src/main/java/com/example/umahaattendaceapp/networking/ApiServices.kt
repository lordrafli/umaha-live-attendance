package com.example.umahaattendaceapp.networking

object ApiServices {

    fun getLiveAttendanceServices(): LiveAttendanceApiServices{
        return RetrofitClient
            .getClient()
            .create(LiveAttendanceApiServices::class.java)
    }

}