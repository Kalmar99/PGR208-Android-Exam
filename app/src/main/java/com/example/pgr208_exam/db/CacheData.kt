package com.example.pgr208_exam.db

import android.os.AsyncTask
import com.example.pgr208_exam.api.AsyncListener

class CacheData<T>(val dao: AbstractDao<T>,val features: ArrayList<T>,val listener: AsyncListener<T>?) : AsyncTask<String, Int, Unit>() {

    override fun doInBackground(vararg params: String?) {
        dao.insert(features)
    }

    override fun onPostExecute(result: Unit?) {
        super.onPostExecute(result)

        listener?.backgroundDownloadComplete();
    }

}