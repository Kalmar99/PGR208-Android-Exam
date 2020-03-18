package com.example.pgr208_exam.api

import android.os.AsyncTask
import com.example.pgr208_exam.gsontypes.collection.FeatureCollection
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception


class GetFeatureCollection(var listener: FeatureCollectionListener?) : AsyncTask<String, Int, FeatureCollection>() {

    override fun doInBackground(vararg params: String?): FeatureCollection {

        publishProgress(0)

        try {

            var response = webRequest(params.get(0)!!)
            var responseList = parseJson(response)
            return responseList;

        } catch(ex:Exception) {
            ex.printStackTrace()
        }

        return FeatureCollection()
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)

        listener?.showProgress(true)

    }

    override fun onPostExecute(result: FeatureCollection) {
        super.onPostExecute(result)

        listener?.showProgress(false)

        if(result != null) {
            if(result.getFeatures().isEmpty()){
                listener?.onFeaturesError()
            }
            else {
                listener?.onFeaturesSuccess(result)
            }
        } else {
            listener?.onFeaturesError()

        }


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

        val collection = gson.fromJson(json,
            FeatureCollection::class.java)

        return collection
    }


}