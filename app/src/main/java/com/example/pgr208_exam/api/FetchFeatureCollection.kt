package com.example.pgr208_exam.api

import android.content.Context
import android.text.PrecomputedText
import android.util.Log
import com.example.pgr208_exam.db.AbstractDao
import com.example.pgr208_exam.db.CacheData
import com.example.pgr208_exam.db.Database
import com.example.pgr208_exam.db.FeatureCollectionDao
import com.example.pgr208_exam.gsontypes.collection.Feature
import com.example.pgr208_exam.gsontypes.collection.FeatureCollection
import com.google.gson.Gson
import java.lang.Exception

class FetchFeatureCollection(listener: AsyncListener<Feature>?,val context: Context) : AbstractFetch<Feature>(listener) {

    override fun doInBackground(vararg params: String?): ArrayList<Feature> {
        publishProgress(0)

        val featureCollectionDao = FeatureCollectionDao(context)

        val features = getDataFromDb(featureCollectionDao,"SELECT * FROM feature_test")

        if(features != null) {
            //if it can find data in database, use that
            Log.i("DATA: ", "from db")
            return features
        } else {
            //If not get data from api
            Log.i("DATA: ", "from Api")
            val features = getDataFromApi(params.get(0))
            //Start a new async task to insert the data in the db
            listener?.downloadInBackground(featureCollectionDao,features)
            return features;
        }

    }


    override fun parseJson(json: String): ArrayList<Feature> {
        var gson = Gson()

        var collection = gson.fromJson(
            json,
            FeatureCollection::class.java
        )

        var ret = ArrayList<Feature>(collection.getFeatures())

        return ret
    }


}