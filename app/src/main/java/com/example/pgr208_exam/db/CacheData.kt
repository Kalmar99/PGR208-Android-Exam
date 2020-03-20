package com.example.pgr208_exam.db

import android.os.AsyncTask
import com.example.pgr208_exam.api.AsyncListener


class CacheData<T>(val dao: AbstractDao<T>,val features: ArrayList<T>,val listener: AsyncListener<T>?) : AsyncTask<String, Int, Unit>() {

    override fun doInBackground(vararg params: String?) {
        publishProgress(0)
        dao.insert(features)
        dao.listener = listener;
    }


    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        listener?.onUpdateBackground(true);
    }


}