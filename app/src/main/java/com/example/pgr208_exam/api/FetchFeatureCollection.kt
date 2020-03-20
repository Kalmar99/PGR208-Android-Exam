package com.example.pgr208_exam.api

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.text.PrecomputedText
import android.util.Log
import com.example.pgr208_exam.db.*
import com.example.pgr208_exam.gsontypes.collection.Feature
import com.example.pgr208_exam.gsontypes.collection.FeatureCollection
import com.google.gson.Gson
import java.lang.Exception

class FetchFeatureCollection(listener: AsyncListener<Feature>?,val context: Context,val db: SQLiteDatabase) : AbstractFetch<Feature>(listener) {

    override fun doInBackground(vararg params: String?): ArrayList<Feature> {
        publishProgress(0)

        val featureCollectionDao = FeatureCollectionDao(context,db,listener)

        val features = getDataFromDb(featureCollectionDao,"SELECT * FROM ${FEATURE_COLLECTION_TABLE}")

        if(features != null) {
            //if it can find data in database, use that
            Log.i("DATA: ", "from db")
            return features
        } else {
            //If not get data from api
            Log.i("DATA: ", "from Api")
            val features = getDataFromApi(params.get(0))
            //Start a new async task to insert the data in the db
            listener?.onDownloadInBackground(featureCollectionDao,features)
            return features;
        }

    }

    override fun parseJson(json: String): ArrayList<Feature> {
        var gson = Gson()

        var collection = gson.fromJson(
            json,
            FeatureCollection::class.java
        )

        var features = ArrayList<Feature>(collection.getFeatures())

        return features
    }


}