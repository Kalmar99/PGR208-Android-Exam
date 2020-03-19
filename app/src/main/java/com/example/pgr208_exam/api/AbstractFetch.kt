package com.example.pgr208_exam.api

import android.os.AsyncTask
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception


abstract class AbstractFetch<T>(var listener: AsyncListener<T>?) : AsyncTask<String, Int, T>() {


    override fun doInBackground(vararg params: String?): T {
        publishProgress(0)

        try {

            var response = webRequest(params.get(0)!!)
            var responseList = parseJson(response)
            return responseList;

        } catch(ex: Exception) {
            ex.printStackTrace()
            throw (ex)
        }

    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)

        listener?.showProgress(true)

    }


     fun webRequest(url : String) : String {

        val client = OkHttpClient()

        val request: Request = Request.Builder()
            .url(url)
            .build()

        var response = client.newCall(request).execute()

        return response.body!!.string()
    }

     abstract fun parseJson(json:String) : T

}