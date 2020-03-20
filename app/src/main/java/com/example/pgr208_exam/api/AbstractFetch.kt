package com.example.pgr208_exam.api

import android.os.AsyncTask
import com.example.pgr208_exam.db.AbstractDao
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception


abstract class AbstractFetch<T>(var listener: AsyncListener<T>?) : AsyncTask<String, Int, ArrayList<T>>() {


    override fun doInBackground(vararg params: String?): ArrayList<T> {
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

    fun getDataFromDb(dao: AbstractDao<T>, sql: String) : ArrayList<T>? {
        val featureArray = dao.fetchAll(sql)
        if(featureArray.isEmpty()) {
            return null;
        } else {
            return featureArray
        }
    }

    fun getDataFromApi(url: String?) : ArrayList<T> {
        try {
            var response = webRequest(url!!)
            var responseList = parseJson(response)
            return responseList;
        } catch (ex: Exception) {
            throw(ex)
        }
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        listener?.showProgress(true)

    }

    override fun onPostExecute(result: ArrayList<T>) {

        super.onPostExecute(result)

        listener?.showProgress(false)

        if (result != null) {
            if (result.isEmpty()) {
                listener?.onFeaturesError()
            } else {
                listener?.onFeaturesSuccess(result)
            }
        } else {
            listener?.onFeaturesError()

        }


    }

    fun webRequest(url : String) : String {

        val client = OkHttpClient()

        val request: Request = Request.Builder()
            .url(url)
            .build()

        var response = client.newCall(request).execute()

        return response.body!!.string()
    }

     abstract fun parseJson(json:String) : ArrayList<T>

}