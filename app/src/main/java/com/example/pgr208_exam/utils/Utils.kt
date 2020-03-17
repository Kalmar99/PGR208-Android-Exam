package com.example.pgr208_exam.utils





import android.content.Context
import android.net.ConnectivityManager


/*
    Copied from: https://github.com/kristiania-android-course/Lecture-09-WebApiApp/blob/gson/NewsApp/app/src/main/java/no/sample/news/utils/Utils.kt
 */

object Utils {

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}