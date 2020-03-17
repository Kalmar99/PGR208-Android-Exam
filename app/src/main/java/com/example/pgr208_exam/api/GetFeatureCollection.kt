package com.example.pgr208_exam.api

import android.os.AsyncTask
import com.example.pgr208_exam.gsontypes.FeatureCollection
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception


class GetFeatureCollection() : AsyncTask<String, Int, FeatureCollection>() {

    override fun doInBackground(vararg params: String?): FeatureCollection {

        try {

            var response = webRequest(params.get(0)!!)
            var responseList = parseJson(response)
            return responseList;

        } catch(ex:Exception) {
            ex.printStackTrace()
        }

        return FeatureCollection()
    }


    private fun webRequest(url : String) : String {

        val client = OkHttpClient()

        val request: Request = Request.Builder()
            .url(url)
            .build()

        var response = client.newCall(request).execute()

        return response.body!!.string()
    }

    private fun parseJson(json:String) : FeatureCollection {
        var gson = Gson()

        val collection = gson.fromJson(json,FeatureCollection::class.java);

        return collection
    }


}