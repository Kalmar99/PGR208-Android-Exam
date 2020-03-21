package com.example.pgr208_exam.api

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.pgr208_exam.db.Database
import com.example.pgr208_exam.db.FEATURE_COLLECTION_TABLE
import com.example.pgr208_exam.db.FeatureCollectionDao
import com.example.pgr208_exam.gsontypes.collection.Feature
import com.example.pgr208_exam.gsontypes.collection.FeatureCollection
import com.google.gson.Gson

class FetchFeatureList(listener: AsyncListener<Feature>,var cacheListener: AsyncCacheListener<Feature>?, val context: Context, val db: SQLiteDatabase) : AbstractFetch<Feature>(listener) {


    override fun doInBackground(vararg params: String?): ArrayList<Feature> {
        publishProgress(0)

        val featureCollectionDao = FeatureCollectionDao(context,db,cacheListener)

        val features = getDataFromDb(featureCollectionDao,"SELECT * FROM $FEATURE_COLLECTION_TABLE")
        if(features != null) {
            Log.i("DATA: ","successfully got data from db")
            return features
        } else {
            Log.w("DB ERROR: ","Cant get features from DB")
            return ArrayList<Feature>()
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