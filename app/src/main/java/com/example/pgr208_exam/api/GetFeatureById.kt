package com.example.pgr208_exam.api

import android.os.AsyncTask
import com.example.pgr208_exam.gsontypes.collection.FeatureCollection
import com.example.pgr208_exam.gsontypes.single.Feature

class GetFeatureById(var listener: FeatureCollectionListener?) : AsyncTask<String, Int, Feature>() {

    override fun doInBackground(vararg params: String?): Feature {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}