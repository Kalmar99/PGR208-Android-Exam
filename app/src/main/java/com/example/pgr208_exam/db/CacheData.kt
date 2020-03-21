package com.example.pgr208_exam.db

import android.os.AsyncTask
import com.example.pgr208_exam.api.AsyncCacheListener
import com.example.pgr208_exam.api.AsyncListener


class CacheData<T>(val dao: AbstractDao<T>,val features: ArrayList<T>,val listener: AsyncCacheListener<T>?) : AsyncTask<String, Int, Unit>() {

    override fun doInBackground(vararg params: String?) {
        publishProgress(0)
        dao.insert(features)
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        listener?.onUpdateBackground(true);
    }

    override fun onPostExecute(result: Unit?) {
        super.onPostExecute(result)
        listener?.onBackgroundDownloadComplete()
    }


}