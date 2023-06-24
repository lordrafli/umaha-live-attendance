package com.example.umahaattendaceapp.date

import java.text.SimpleDateFormat
import java.util.*

object MyDate {
    fun getCurrentDateForServer(): String{
        val currentTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(currentTime)
    }
}